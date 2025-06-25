package com.clinic.clinic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clinic.clinic.repository.PatientRepository;

@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}
	// @Bean
	// public CommandLineRunner printPatients(PatientRepository patientRepository) {
	// return args -> {
	// patientRepository.findAll().forEach(System.out::println);
	// };
	// }

}
