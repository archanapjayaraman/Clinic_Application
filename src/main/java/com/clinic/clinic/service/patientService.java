package com.clinic.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
