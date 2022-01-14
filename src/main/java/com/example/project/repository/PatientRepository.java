package com.example.project.repository;

import com.example.project.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,String>{
    Patient findByPatientname(String patientname);
    Patient findByPatientid(Long patientid);
	void deleteByPatientname(String patientname);
}
