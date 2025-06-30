package com.clinic.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.clinic.clinic.patient.Patient;
import com.clinic.clinic.repository.PatientRepository;

@Service
public class patientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Add methods to handle patient-related operations
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public boolean isUserRegistered(String username) {
        return userDetailsManager.userExists(username);
    }

    public void registerPatient(Patient patient) {
        // Create user for Spring Security (email as username)
        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername(patient.getEmail())
                .password(passwordEncoder.encode(patient.getPassword()))
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);

        // Save patient in your own table
        patientRepository.save(patient);
    }

    public Patient findPatientByEmail(String email) {
        return patientRepository.findByEmail(email).orElse(null);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

}
