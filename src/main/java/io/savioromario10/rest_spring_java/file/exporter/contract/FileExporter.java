package io.savioromario10.rest_spring_java.file.exporter.contract;

import io.savioromario10.rest_spring_java.data.dto.v1.PersonDTO;
import org.springframework.core.io.Resource;

import java.util.List;

public interface FileExporter {

    Resource exportFile(List<PersonDTO> people) throws Exception;
}