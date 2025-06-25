package com.clinic.clinic.Controller;

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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "PatientRegistration";
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute("patient") Patient patient, Model model) {
        System.out.println(patient);
        patientService.savePatient(patient);
        // check patient exist
        Patient registeredPatient = patientService.findPatientById(patient.getId());
        if (registeredPatient != null) {
            System.out.println("Patient registered successfully: " + registeredPatient);
            model.addAttribute("message", "Patient registered successfully!");
        } else {
            model.addAttribute("message", "Registration failed. Please try again.");
        }
        patient = patientService.findPatientById(patient.getId());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        return "redirect:/patient/" + patient.getId();
    }

    @GetMapping("/patient/{id}")
    public String getMethodName(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.findPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("message", "Patient details retrieved successfully!");
        return "registration_success";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "PatientLogin";
    }

    @PostMapping("/patientlogin")
    public String loginPatient(@ModelAttribute("patient") Patient patient, Model model) {
        System.out.println(patient);
        model.addAttribute("message", "Hello!");
        return "login_success";
    }

}
