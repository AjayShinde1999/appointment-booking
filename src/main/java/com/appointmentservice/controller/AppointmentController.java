package com.appointmentservice.controller;

import com.appointmentservice.entity.Appointment;
import com.appointmentservice.payload.Doctor;
import com.appointmentservice.payload.Patient;
import com.appointmentservice.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {

        ResponseEntity<Patient> patientResponse = restTemplate.getForEntity(
                "http://localhost:8080/patient/" + appointment.getPatientId(),
                Patient.class);

        if (patientResponse.getStatusCode() != HttpStatus.OK || patientResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Patient patient = patientResponse.getBody();
        log.info("Patient details: {}",patient);

        ResponseEntity<Doctor> doctorResponse = restTemplate.getForEntity(
                "http://localhost:9191/doctor/" + appointment.getDoctorId(),
                Doctor.class);

        if (doctorResponse.getStatusCode() != HttpStatus.OK || doctorResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Doctor doctor = doctorResponse.getBody();
        log.info("Doctor Details: {}",doctor);

        Appointment saveAppointment = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(saveAppointment, HttpStatus.CREATED);

    }


}
