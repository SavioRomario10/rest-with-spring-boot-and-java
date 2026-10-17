package io.savioromario10.rest_spring_java.file.importer.contract;

import java.io.InputStream;
import java.util.List;

import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;

public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;
}