package com.example.pathalogy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.BloodTest;
import com.example.pathalogy.entity.ThyroidTest;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.BloodTestRepo;
import com.example.pathalogy.repository.ThyroidTestRepo;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfReportService {

    @Autowired
    private BloodTestRepo bloodReportRepo;
    
    @Autowired
    private ThyroidTestRepo thyroidTestRepo;
    
    
    //-------------------------------------------------------------------------------------------

//    public BloodTest saveBloodReport(BloodTest bloodReport) {
//        return bloodReportRepo.save(bloodReport);
//    }
//    
//    public ThyroidTest saveThyroidReport(ThyroidTest thyroidTestReport) {
//        return thyroidTestRepo.save(thyroidTestReport);
//    }
//    
    //---------------------------------------------------------------------------------------------

    public byte[] generatePdfReportofBloodTest(Long reportId) throws IOException {
        BloodTest bloodTestReport = bloodReportRepo.findById(reportId)
                .orElseThrow(() -> new CustomException("Blood test Id Not Found"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Add Title
        document.add(new Paragraph("Baneshwar Smart Pathology Lab \n BLOOD TEST (BT)").setFontSize(14).setBold());

        // Add Patient Information
        document.add(new Paragraph("Patient Name: " + bloodTestReport.getPatientIdRef().getName()));
        document.add(new Paragraph("Age / Sex: " + bloodTestReport.getPatientIdRef().getGender()));
        document.add(new Paragraph("Referred By: " + bloodTestReport.getDoctorIdRef().getFullName()));
        document.add(new Paragraph("Reg. no. / UHID: " + bloodTestReport.getBloodTestId() + " / " + bloodTestReport.getPatientIdRef().getPatientId()));
        document.add(new Paragraph("Investigations: Complete Blood Count (CBC)"));

        // Add Table with fetched values
        Table table = new Table(4); // Number of columns
        table.addCell("Test Name");
        table.addCell("Value");
        table.addCell("Unit");
        table.addCell("Reference Range");

        // Add data rows for each blood test parameter
        table.addCell("White Blood Cells (WBC)");
        table.addCell(String.valueOf(bloodTestReport.getWhiteBloodCells()));
        table.addCell("cells/mcL");
        table.addCell("4,000 - 11,000 cells/mcL");

        table.addCell("Neutrophils");
        table.addCell(String.valueOf(bloodTestReport.getNeutrophils()));
        table.addCell("cells/mcL");
        table.addCell("40% - 60%");

        table.addCell("Lymphocytes");
        table.addCell(String.valueOf(bloodTestReport.getLymphocytes()));
        table.addCell("cells/mcL");
        table.addCell("20% - 40%");

        table.addCell("Monocytes");
        table.addCell(String.valueOf(bloodTestReport.getMonocytes()));
        table.addCell("cells/mcL");
        table.addCell("2% - 8%");

        table.addCell("Eosinophils");
        table.addCell(String.valueOf(bloodTestReport.getEosinophils()));
        table.addCell("cells/mcL");
        table.addCell("1% - 4%");

        table.addCell("Basophils");
        table.addCell(String.valueOf(bloodTestReport.getBasophils()));
        table.addCell("cells/mcL");
        table.addCell("0.5% - 1%");

        table.addCell("Red Blood Cells (RBC)");
        table.addCell(String.valueOf(bloodTestReport.getRedBloodCells()));
        table.addCell("cells/mcL");
        table.addCell("4.5 - 5.9 million cells/mcL");

        table.addCell("Hemoglobin");
        table.addCell(String.valueOf(bloodTestReport.getHemoglobin()));
        table.addCell("g/dL");
        table.addCell("13.5 - 17.5 g/dL");

        table.addCell("Hematocrit");
        table.addCell(String.valueOf(bloodTestReport.getHematocrit()));
        table.addCell("%");
        table.addCell("41% - 53%");

        table.addCell("Mean Corpuscular Volume (MCV)");
        table.addCell(String.valueOf(bloodTestReport.getMeanCorpuscularVolume()));
        table.addCell("fL");
        table.addCell("80 - 100 fL");

        table.addCell("Mean Corpuscular Hemoglobin (MCH)");
        table.addCell(String.valueOf(bloodTestReport.getMeanCorpuscularHemoglobin()));
        table.addCell("pg/cell");
        table.addCell("26 - 34 pg/cell");

        table.addCell("Mean Corpuscular Hemoglobin Concentration (MCHC)");
        table.addCell(String.valueOf(bloodTestReport.getMeanCorpuscularHemoglobinConcentration()));
        table.addCell("g/dL");
        table.addCell("32 - 36 g/dL");

        table.addCell("Red Cell Distribution Width (RDW)");
        table.addCell(String.valueOf(bloodTestReport.getRedCellDistributionWidth()));
        table.addCell("%");
        table.addCell("11% - 15%");

        table.addCell("Platelets");
        table.addCell(String.valueOf(bloodTestReport.getPlatelets()));
        table.addCell("cells/mcL");
        table.addCell("150,000 - 450,000 cells/mcL");

        table.addCell("Mean Platelet Volume (MPV)");
        table.addCell(String.valueOf(bloodTestReport.getMeanPlateletVolume()));
        table.addCell("fL");
        table.addCell("7.5 - 11.5 fL");

        table.addCell("Platelet Distribution Width (PDW)");
        table.addCell(String.valueOf(bloodTestReport.getPlateletDistributionWidth()));
        table.addCell("%");
        table.addCell("9% - 14%");

        document.add(table);

        // Add Remarks
        if (bloodTestReport.getRemark() != null && !bloodTestReport.getRemark().isEmpty()) {
            document.add(new Paragraph("\nRemarks: " + bloodTestReport.getRemark()));
        }

        // Add Footer
        document.add(new Paragraph("\n~~~ End of report ~~~"));
        document.add(new Paragraph("\nLab Incharge: Mr. Prashant Bhosale , DMLT"));
        document.add(new Paragraph("Pathologist: Dr. Ankit Jagtap, MBBS, MD"));

        document.close();
        writer.close();

        return byteArrayOutputStream.toByteArray();
    }

    
    //-----------------------------------------------------------------------------------------
    
    public byte[] generatePdfReportofThyroidTest(Long reportId) throws IOException {
		ThyroidTest thyroidTestReport= thyroidTestRepo.findById(reportId).orElseThrow(() -> new CustomException("Blood test Id Not Found"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Add Title
        document.add(new Paragraph("HAEMATOLOGY\nCOMPLETE BLOOD COUNT (CBC)").setFontSize(14).setBold());

        // Add Patient Information
        document.add(new Paragraph("Patient Name: " + thyroidTestReport.getPatientRef().getName()));
        document.add(new Paragraph("Age / Sex: " + thyroidTestReport.getPatientRef().getGender()));
        document.add(new Paragraph("Referred By: " + thyroidTestReport.getDoctorId().getFullName()));
        document.add(new Paragraph("Reg. no. / UHID: " + thyroidTestReport.getThyroidTestId() + " / " + thyroidTestReport.getPatientRef().getPatientId()));
        document.add(new Paragraph("Investigations: Complete Blood Count (CBC)"));

        // Add Table
        Table table = new Table(4); // Number of columns
        table.addCell("ThyroidTEST");
        table.addCell("VALUE");
        table.addCell("UNIT");
        table.addCell("REFERENCE");


        document.add(table);

        // Add Footer
        document.add(new Paragraph("\n~~~ End of report ~~~"));
        document.add(new Paragraph("\nLab Incharge: Mr. Sachin Sharma, DMLT"));
        document.add(new Paragraph("Pathologist: Dr. A. K. Asthana, MBBS, MD"));

        document.close();
        writer.close();

        return byteArrayOutputStream.toByteArray();
    }
}
