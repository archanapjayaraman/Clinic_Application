package com.clinic.clinic.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinic.clinic.patient.Patient;

@Controller
public class PatientController {
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
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        model.addAttribute("patient", patient);
        model.addAttribute("message", "Registration successful!");
        return "registration_success";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "PatientLogin";
    }

    @PostMapping("/patientlogin")
    public String loginPatient(@ModelAttribute("patient") Patient patient, Model model) {
        Patient fullPatient = patientService.findByEmail(patient.getEmail());
    if (fullPatient != null && fullPatient.getPassword().equals(patient.getPassword())) {
        System.out.println(patient);
        model.addAttribute("message", "Hello!");
        return "login_success";
    }

}
