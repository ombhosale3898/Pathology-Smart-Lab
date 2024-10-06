package com.example.pathalogy.controller;

import java.io.ByteArrayInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import com.example.pathalogy.entity.BloodTest;
import com.example.pathalogy.entity.ThyroidTest;
import com.example.pathalogy.service.BloodTestService;
import com.example.pathalogy.service.PdfReportService;
import com.example.pathalogy.service.ThyroidTestService;
import com.itextpdf.io.exceptions.IOException;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class PdfReportController {

	@Autowired
    private PdfReportService pdfReportService;
	
	@Autowired
	private BloodTestService bloodTestService;
	
	@Autowired 
	ThyroidTestService thyroidTestService;

	//------------------------------------------------------------------------------------------
    @PostMapping("/bloodTestReport")
    public BloodTest saveBloodTestReport(@RequestBody BloodTest bloodTestReport) {
        return bloodTestService.saveBloodTest(bloodTestReport);
    }
    
    //-----------------------------------------------------------------------------------------
    
    @PostMapping("/thyroidtestReport")
    public ThyroidTest saveThyroidTestReport(@RequestBody ThyroidTest thyroidTestReport) {
        return thyroidTestService.saveThyroidTest(thyroidTestReport);
    }
    

    //------------------------------------------------------------------------------------------
    @GetMapping("/pdf/blood/{reportId}")
    public ResponseEntity<InputStreamResource> generatePdfReportofBloodTest(@PathVariable Long reportId) throws IOException, java.io.IOException {
        byte[] pdfReport = pdfReportService.generatePdfReportofBloodTest(reportId);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfReport);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cbc_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }
    
    //-----------------------------------------------------------------------------------------------
    
    @GetMapping("/pdf/thyroid/{reportId}")
    public ResponseEntity<InputStreamResource> generatePdfReportThyroidTest(@PathVariable Long reportId) throws IOException, java.io.IOException {
        byte[] pdfReport = pdfReportService.generatePdfReportofThyroidTest(reportId);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfReport);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cbc_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }
    
    //-----------------------------------------------------------------------------------------------
	
}
