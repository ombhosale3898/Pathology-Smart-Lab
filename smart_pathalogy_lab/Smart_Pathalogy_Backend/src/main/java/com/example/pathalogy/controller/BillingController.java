package com.example.pathalogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pathalogy.entity.Billing;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.BillingService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/billing")
@CrossOrigin("*")
public class BillingController {

	@Autowired
	private BillingService billingServiceRef;

	// -------------------------------------------------------------------------------------

	@PostMapping("/create")
	public ResponseEntity<?> createBilling(@RequestBody Billing createBilling) {

		System.out.println(createBilling);
		createBilling.setBillingDate(LocalDate.now());
		return new ResponseEntity<>(billingServiceRef.saveBilling(createBilling), HttpStatus.OK);

	}
	// -----------------------------------------------------------------------------------

	@GetMapping("/getAllBills")
	public ResponseEntity<?> getAllBillingOrder() {

		try {
			return new ResponseEntity<>(billingServiceRef.getAllBills(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// --------------------------------------------------------------------------------

	@GetMapping("/id{id}")
	public ResponseEntity<?> getBillingOrdersById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(billingServiceRef.getBillById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@PutMapping("/updateBilling")
	public ResponseEntity<?> updateBillingOrder(@RequestBody Billing updatedBilling) {
		try {

			return new ResponseEntity<>(billingServiceRef.updateBills(updatedBilling), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// -----------------------------------------------------------------------------------

	@DeleteMapping("/deleteBilling")
	public ResponseEntity<?> deleteBillingOrder(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(billingServiceRef.deleteBilling(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// --------------------------------------------------------------------------------------

	@PutMapping("/{id}/status/paid")
	public ResponseEntity<Billing> markAsPaid(@RequestBody Billing updateStatus) {
		try {

			Billing updatedBillingOrder = billingServiceRef.updateBillingStatus(updateStatus, "paid");

			return ResponseEntity.ok(updatedBillingOrder);

		} catch (CustomException e) {

			return ResponseEntity.notFound().build();
		}
	}

	// -----------------------------------------------------------------------------------
	@PutMapping("/{id}/status/Unpaid")
	public ResponseEntity<Billing> markAsUnPaid(@RequestBody Billing updateStatus) {
		try {

			Billing updatedBillingOrder = billingServiceRef.updateBillingStatus(updateStatus, "Unpaid");

			return ResponseEntity.ok(updatedBillingOrder);

		} catch (CustomException e) {

			return ResponseEntity.notFound().build();
		}
	}
	
	//----------------------------------------------------------------------------------------
	
    @GetMapping("/api/bill/pdf/{billingId}")
    public ResponseEntity<InputStreamResource> generatePdfBill(@PathVariable Long billingId) throws IOException {
        byte[] pdfBill = billingServiceRef.generatePdfBill(billingId);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBill);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=bill.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }
	
	//----------------------------------------------------------------------------------------
    
}
