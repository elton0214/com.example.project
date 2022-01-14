package com.example.project;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;

@SpringBootApplication
public class HealthCareServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareServiceApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner run(PatientService patService) {
//		return args -> {
//			Patient patient1 = new Patient("John","john@gmail.com","0988111", new Date());
////			Patient patient1 = new Patient("John","john@gmail.com","0988111", new Date());
//			patService.savePatient(patient1);
//		};
//	}
		

}
