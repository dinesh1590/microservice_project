package com.trackingsystem.departmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWithWork {

    private Employee employee;
    private List<EmployeeWorkEntity> work;
}
