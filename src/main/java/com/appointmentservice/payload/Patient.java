package com.appointmentservice.payload;

import lombok.Data;

@Data
public class Patient {

    private Long id;
    private String name;
    private int age;
}
