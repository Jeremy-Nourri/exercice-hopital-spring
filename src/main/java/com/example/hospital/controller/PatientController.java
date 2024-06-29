package com.example.hospital.controller;

import com.example.hospital.model.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.hospital.service.PatientService;

@Controller
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println(patientService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "index";
    }

    @GetMapping("/patient/form")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @PostMapping("/patient/create-update")
    public String createOrUpdatePatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patient-form";
        } else {
            if(patient.getId() == null) {
                patientService.add(patient);
            } else {
                patientService.update(patient);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/patient/{id}")
    public String displayPatientProfile(@PathVariable("id") Long id, Model model) {
        System.out.println(patientService.findById(id));
        model.addAttribute("patient", patientService.findById(id));
        return "patient";
    }

    @GetMapping("/patient/delete")
    public String deletePatient(@RequestParam("id") Long id) {
        patientService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/patient/update")
    public String updatePatient(@RequestParam("id") Long id, Model model) {
        model.addAttribute("patient", patientService.findById(id));
        return "patient-form";
    }
}
