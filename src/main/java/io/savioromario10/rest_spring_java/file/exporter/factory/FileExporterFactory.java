package io.savioromario10.rest_spring_java.file.exporter.factory;

import io.savioromario10.rest_spring_java.exception.BadRequestException;
import io.savioromario10.rest_spring_java.file.exporter.MediaTypes;
import io.savioromario10.rest_spring_java.file.exporter.contract.FileExporter;
import io.savioromario10.rest_spring_java.file.exporter.impl.CsvExporter;
import io.savioromario10.rest_spring_java.file.exporter.impl.XlsxExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileExporterFactory {

    private Logger logger = LoggerFactory.getLogger(FileExporterFactory.class);

    @Autowired
    private ApplicationContext context;

    public FileExporter getExporter(String acceptHeader) throws Exception{

        if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)){

            logger.info("XLSX file detected");

            return context.getBean(XlsxExporter.class);

        } else {

            if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)) {

                logger.info("CSV file detected");

                return context.getBean(CsvExporter.class);

            }else {

                logger.error("File type not supported!");

                throw new BadRequestException("File type not supported!");
            }
        }
    }
}