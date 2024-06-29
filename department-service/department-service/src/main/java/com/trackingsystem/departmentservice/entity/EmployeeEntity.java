package com.trackingsystem.departmentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Employees")
public class EmployeeEntity {

    @Id
    @Column(name = "employee_id", nullable=false, unique=true)
    private int employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "team")
    private String team;

    @Column(name = "mail", unique = true)
    private String mail;

    @Column(name = "phone", unique = true)
    private String phone;

}
