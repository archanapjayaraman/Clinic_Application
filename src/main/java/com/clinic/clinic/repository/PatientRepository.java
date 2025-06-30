package com.clinic.clinic.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinic.clinic.patient.Patient;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Additional query methods can be defined here if needed
    Optional<Patient> findByEmail(String email);
}
