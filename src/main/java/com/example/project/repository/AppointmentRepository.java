package com.example.project.repository;

import com.example.project.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String>{
    Optional<Appointment> findByBookingid(String booking_id);
    List<Optional<Appointment>> findByPatientname(String patient_id);
}
