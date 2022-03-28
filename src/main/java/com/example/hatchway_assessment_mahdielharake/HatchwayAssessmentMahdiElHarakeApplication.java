package com.example.hatchway_assessment_mahdielharake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HatchwayAssessmentMahdiElHarakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HatchwayAssessmentMahdiElHarakeApplication.class, args);
    }

}
