package com.clinic.clinic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinic.clinic.patient.Patient;
import com.clinic.clinic.repository.PatientRepository;
import com.clinic.clinic.service.patientService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
    @Autowired
    private patientService patientService;

    @RequestMapping("/patients")
    public String getPatients() {
        return "patients";
    }

    // to get all the patients
    @GetMapping("/patients")
    public List<Patient> getAllUsers() {
        return patientService.findAllPatients();
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "PatientRegistration";
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute("patient") Patient patient, Model model) {
        // Check if user already exists by email
        if (patientService.isUserRegistered(patient.getEmail())) {
            model.addAttribute("message", "User already registered with this email.");
            return "PatientRegistration";
        }

        // Save user for authentication and as patient
        patientService.registerPatient(patient);

        model.addAttribute("message", "Patient registered successfully!");
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "login";
    }

    @PostMapping("/login")
    public String loginPatient(@ModelAttribute("patient") Patient patient, Model model) {
        System.out.println(patient);
        model.addAttribute("message", "Hello!");
        return "redirect:/patient/" + patient.getId();

    }

    @GetMapping("/patient/{id}")
    public String getMethodName(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.findPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("message", "Patient details retrieved successfully!");
        return "login_success";
    }

}
