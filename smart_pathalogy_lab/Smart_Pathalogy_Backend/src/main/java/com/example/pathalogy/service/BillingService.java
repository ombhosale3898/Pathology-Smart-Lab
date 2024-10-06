package com.example.pathalogy.service;



import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Billing;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.BillingRepo;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import jakarta.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;


@Service
public class BillingService {

	@Autowired
	private  BillingRepo billingRepoRef;
	@Autowired
	private EmailService emailService;
	
	//-----------------------------------------------------------------------------------------------
	
	public Billing saveBilling(Billing billing) {
        billing.setBillingDate(LocalDate.now());
        return billingRepoRef.save(billing);
    }
	//----------------------------------------------------------------------------------------------
	
	public List<Billing> getAllBills() {

		List<Billing> BillingList = billingRepoRef.findAll();

		if (BillingList.size() != 0) {

			return BillingList;
		} else {

			throw new CustomException("Bills are not available");
		}
	}
	
	//------------------------------------------------------------------------------------------------
	
	public Billing getBillById(Long id) {

		return billingRepoRef.findById(id).orElseThrow(() -> new CustomException("Id Not Found"));

	}
	
	//-----------------------------------------------------------------------------------------------
	
	public Billing updateBills(Billing updatedBilling) {

		Billing existingBilling = billingRepoRef.findById(updatedBilling.getId())
				.orElseThrow(() -> new CustomException("Billing  Id is  Not Found for Billing Updation"));

		if (existingBilling!=null) {

			
			existingBilling.setPatientIdRef(updatedBilling.getPatientIdRef());
            existingBilling.setTestOrderRef(updatedBilling.getTestOrderRef());
            existingBilling.setAmount(updatedBilling.getAmount());
            existingBilling.setStatus(updatedBilling.getStatus());
            existingBilling.setBillingDate(LocalDate.now());

			
			return billingRepoRef.save(existingBilling);

		} else {

			throw new CustomException(" Billing not found with given id");
		}
	}
	//---------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> deleteBilling(Long id) {

		Billing billingOrder = billingRepoRef.findById(id).orElseThrow(() -> new CustomException("Order is not found with given id"));

		if (billingOrder != null) {

			billingRepoRef.delete(billingOrder);
			return new ResponseEntity<>("Order Deleted successfully", HttpStatus.OK);
		}

		else {
			
			throw new CustomException("Unable to delete Billing Order");
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
	public Billing updateBillingStatus(Billing updateStatus, String status) {
        Billing billingOrder = billingRepoRef.findById(updateStatus.getId())
                .orElseThrow(() -> new CustomException("TestOrder not found for this id "));
        billingOrder.setStatus(status);
        return billingRepoRef.save(billingOrder);
    }
	
	//-------------------------------------------------------------------------------------------
	
	
	public byte[] generatePdfBill(Long billingId) throws IOException {
        Optional<Billing> optionalBilling = billingRepoRef.findById(billingId);
        if (!optionalBilling.isPresent()) {
            throw new RuntimeException("Billing record not found");
        }

        Billing billing = optionalBilling.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Add Title
        document.add(new Paragraph("Pathology Test Bill").setFontSize(14).setBold());

        // Create a table with two columns
        float[] columnWidths = {150f, 150f};
        Table table = new Table(columnWidths);

        // Add table headers
        table.addHeaderCell(new Cell().add(new Paragraph("Description")));
        table.addHeaderCell(new Cell().add(new Paragraph("Details")));

        // Add Patient Information in table format
        table.addCell(new Cell().add(new Paragraph("Patient Name")));
        table.addCell(new Cell().add(new Paragraph(billing.getPatientIdRef().getName())));


        table.addCell(new Cell().add(new Paragraph("Test Name")));
        table.addCell(new Cell().add(new Paragraph(billing.getTestOrderRef().getTestType())));

        table.addCell(new Cell().add(new Paragraph("Billing Date")));
        table.addCell(new Cell().add(new Paragraph(billing.getBillingDate().toString())));

        table.addCell(new Cell().add(new Paragraph("Status")));
        table.addCell(new Cell().add(new Paragraph(billing.getStatus())));

        table.addCell(new Cell().add(new Paragraph("Amount")));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(billing.getAmount()))));

        document.add(table);

        // Add Footer
        document.add(new Paragraph("\n~~~ End of bill ~~~"));

        document.close();
        
        
     // Send email with PDF attachment
        try {
            emailService.sendBillingEmail(
                billing.getPatientIdRef().getEmail(),
                "Your Pathology Test Bill",
                "Dear " + billing.getPatientIdRef().getName() + ",\n\nPlease find attached your pathology test bill.\n\nBest regards,\nPathology Lab",
                byteArrayOutputStream.toByteArray(),
                "Pathology_Test_Bill.pdf"
            );
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
 
        return byteArrayOutputStream.toByteArray();
    }
	
//----------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
}
