package io.savioromario10.rest_spring_java.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog(){
        logger.info("INFO");
        logger.debug("DEBUG");
        logger.warn("WARN");
        logger.error("ERROR");

        return "Logs generated successfully";
    }
}
