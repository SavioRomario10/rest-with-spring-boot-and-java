package io.savioromario10.rest_spring_java.file.importer.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.savioromario10.rest_spring_java.file.importer.contract.FileImporter;
import io.savioromario10.rest_spring_java.exception.BadRequestException;
import io.savioromario10.rest_spring_java.file.importer.impl.CsvImporter;
import io.savioromario10.rest_spring_java.file.importer.impl.XlsxImporter;

@Component
public class FileImporterFactory {

    private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public FileImporter getImporter(String fileName) throws Exception{
        if(fileName.endsWith(".xlsx")){

            logger.info("XLSX file detected");

            return context.getBean(XlsxImporter.class);

        } else if (fileName.endsWith(".csv")) {

            logger.info("CSV file detected");

            return context.getBean(CsvImporter.class);

        }else {

            logger.error("File type not supported!");

            throw new BadRequestException("File type not supported!");
        }
    }
}