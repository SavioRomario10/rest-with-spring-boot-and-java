package io.savioromario10.rest_spring_java.file.importer.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;
import io.savioromario10.rest_spring_java.file.importer.contract.FileImporter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class XlsxImporter implements FileImporter {

    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {

        Iterator<Row> rowIterator;
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) rowIterator.next();

            return parseRowsToPersonDtoList(rowIterator);
        }
    }

    private List<PersonDTO> parseRowsToPersonDtoList(Iterator<Row> rowIterator) {

        List<PersonDTO> people = new ArrayList<>();

        while(rowIterator.hasNext()){

            Row row = rowIterator.next();

            if(isRowValid(row)){
                people.add(parseRowsToPersonDto(row));
            }

        }

        return people;
    }

    private PersonDTO parseRowsToPersonDto(Row row) {

        PersonDTO person = new PersonDTO();

        person.setFirstName(row.getCell(0).getStringCellValue());
        person.setLastName(row.getCell(1).getStringCellValue());
        person.setAddress(row.getCell(2).getStringCellValue());
        person.setGender(row.getCell(3).getStringCellValue());
        person.setEnabled(true);

        return person;
    }

    private static boolean isRowValid(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }
}