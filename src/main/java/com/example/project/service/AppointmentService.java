package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.Model.Appointment;
import com.example.project.Model.Patient;
import com.example.project.repository.AppointmentRepository;
import com.example.project.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class AppointmentService{
	
	private final PatientRepository patRepo;
	private final AppointmentRepository appmtRepo;
	public AppointmentService(PatientRepository patRepo, AppointmentRepository appmtRepo) {
        this.patRepo = patRepo;
        this.appmtRepo = appmtRepo;
    }
	

    public String saveAppointment(Appointment appmt) {
        appmtRepo.save(appmt);
        return "{message=\"Booking successful\"}";
    }

    public Patient savePatient(Patient pat) {
        return patRepo.save(pat);
    }
    
    public Optional<Appointment> findByBookingid(String booking_id) {
        return appmtRepo.findByBookingid(booking_id);
    }
    
    public List<Optional<Appointment>> findByPatientname(String pat_id) {
        return appmtRepo.findByPatientname(pat_id);
    }
    
    public List<Appointment> getAppointments() {
        return appmtRepo.findAll();
    }

    public void deleteAppointment(String booking_id) {
        appmtRepo.deleteById(booking_id);
    }
}
