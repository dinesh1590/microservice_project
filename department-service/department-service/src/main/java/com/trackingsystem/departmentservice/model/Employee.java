package com.trackingsystem.departmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int employeeId;
    private String name;
    private String team;
    private String mail;
    private String phone;

}
