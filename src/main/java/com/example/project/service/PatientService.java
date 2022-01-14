package com.example.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.project.Model.Patient;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.repository.PatientRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class PatientService {
	
	private final PatientRepository patRepo;
	public PatientService(PatientRepository patRepo) {
        this.patRepo = patRepo;
    }

    public String savePatient(Patient pat) {
            patRepo.save(pat);
            System.out.println("patRepo.save(pat):" + pat.toString());
            return "{message=\"Registration successful\"}";
    }

    public Optional<Patient> getPatient(String patientname) {
        return Optional.of(patRepo.findByPatientname(patientname));
    }
    
//    public Optional<Patient> getPatient(Long patientid) {
//        return Optional.of(patRepo.findByPatientid(patientid));
//    }

    public List<Patient> getPatients(){
        return patRepo.findAll();
    }

    public void deletePatient(String patientname) {
        patRepo.deleteByPatientname(patientname);
    }

}
