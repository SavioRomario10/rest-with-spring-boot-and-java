package io.savioromario10.rest_spring_java.file.importer.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;
import io.savioromario10.rest_spring_java.file.importer.contract.FileImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvImporter implements FileImporter {

    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {

        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));

        return parseRecordsToPersonDTO(records);
    }

    private List<PersonDTO> parseRecordsToPersonDTO(Iterable<CSVRecord> records) {

        List<PersonDTO> people = new ArrayList<>();

        for (CSVRecord record : records){

            PersonDTO person = new PersonDTO();

            person.setFirstName(record.get("firstName"));
            person.setLastName(record.get("lastName"));
            person.setAddress(record.get("address"));
            person.setGender(record.get("gender"));
            person.setEnabled(true);

            people.add(person);
        }

        return people;
    }
}