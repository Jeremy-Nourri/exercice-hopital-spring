package com.example.hospital.service;

import com.example.hospital.model.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements IBaseService<Patient> {

    List<Patient> patientList = new ArrayList<Patient>();
    Long currentId = 1L;

    public PatientService() {
        Patient patient = Patient.builder()
                .id(currentId++)
                .lastName("Doe")
                .firstName("John")
                .birthDate(LocalDate.of(1980, 1, 1))
                .build();
        patientList.add(patient);
    }

    @Override
    public Patient add(Patient patient) {
        patient.setId(currentId++);
        patientList.add(patient);
        return patient;
    }

    @Override
    public Patient update(Patient patient) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        Patient patient = findById(id);
        return patientList.remove(patient);
    }

    @Override
    public Patient findById(long id) {
        return patientList.stream()
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Patient not found"));
    }

    @Override
    public List<Patient> findAll() {
        return patientList;
    }

    public List<Patient> searchPatients(String nameSought) {
        return patientList.stream()
                .filter(patient -> patient.getLastName().equalsIgnoreCase(nameSought) || patient.getFirstName().equalsIgnoreCase(nameSought))
                .toList();
    }
}
