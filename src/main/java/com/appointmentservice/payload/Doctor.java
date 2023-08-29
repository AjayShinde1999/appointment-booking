package com.appointmentservice.payload;

import lombok.Data;

@Data
public class Doctor {

    private Long id;
    private String name;
    private String specialization;
}
