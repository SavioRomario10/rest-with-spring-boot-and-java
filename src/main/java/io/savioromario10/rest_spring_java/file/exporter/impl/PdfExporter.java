package io.savioromario10.rest_spring_java.file.exporter.impl;

import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;
import io.savioromario10.rest_spring_java.file.exporter.contract.FileExporter;
import io.savioromario10.rest_spring_java.service.QrCodeService;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PdfExporter implements FileExporter {

    @Autowired
    private QrCodeService service;

    @Override
    public Resource exportFile(List<PersonDTO> people) throws Exception {

        InputStream inputStream = getClass().getResourceAsStream("/templates/people.jrxml");

        if(inputStream == null){
            throw new RuntimeException("Template file not found: /templates/people.jrxml");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(people);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return new ByteArrayResource(outputStream.toByteArray());
        }
    }

    @Override
    public Resource exportPerson(PersonDTO person) throws Exception {
        InputStream mainTemplateStream = getClass().getResourceAsStream("/templates/person.jrxml");

        if(mainTemplateStream == null){
            throw new RuntimeException("Template file not found: /templates/person.jrxml");
        }

        InputStream subReportStream = getClass().getResourceAsStream("/templates/books.jrxml");

        if(subReportStream == null){
            throw new RuntimeException("Template file not found: /templates/books.jrxml");
        }

        JasperReport mainReport = JasperCompileManager.compileReport(mainTemplateStream);
        JasperReport subReport = JasperCompileManager.compileReport(subReportStream);

        InputStream qrCodeStream = service.generateQrCode(person.getProfile_url(), 200, 200);

        JRBeanCollectionDataSource subDataSource = new JRBeanCollectionDataSource(person.getBooks());

        Map<String, Object> parameters = new HashMap<>();
            parameters.put("SUB_REPORT_DATA_SOURCE", subDataSource);
            parameters.put("BOOK_SUB_REPORT", subReport);
            parameters.put("QR_PARAMETER", qrCodeStream);

        JRBeanCollectionDataSource mainDataSource = new JRBeanCollectionDataSource(Collections.singletonList(person));

        JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameters, mainDataSource);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return new ByteArrayResource(outputStream.toByteArray());
        }
    }
}