package com.example.project.controller;

import com.example.project.Model.Appointment;
import com.example.project.repository.AppointmentRepository;
import com.example.project.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/register")
    public String createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    // GET an appointment
    @GetMapping("/view/{id}")
    public @ResponseBody
    Optional<Appointment> getAppointment(@PathVariable("id") String id) throws Exception {
        return appointmentService.findByBookingid(id);
    }

    // GET appointment list appointed by a patient
    @GetMapping("/list/{patientname}")
    public @ResponseBody
    List<Optional<Appointment>> getAppmtsFromPatient(@PathVariable("patientname") String patientname) throws Exception {
        return appointmentService.findByPatientname(patientname);
    }

    // GET appointments
    @GetMapping("/list")
    List<Appointment> all() {
        return appointmentService.getAppointments();
    }

    // DELETE a appointment
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void deleteAppointment(@PathVariable("id") String id) throws Exception {
    	appointmentService.deleteAppointment(id);
    }
}
