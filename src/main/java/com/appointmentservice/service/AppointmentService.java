package com.appointmentservice.service;

import com.appointmentservice.entity.Appointment;
import com.appointmentservice.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }
}
