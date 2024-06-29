package com.trackingsystem.employeeservice.controller;


import com.trackingsystem.employeeservice.entity.EmployeeWorkEntity;
import com.trackingsystem.employeeservice.model.Employee;
import com.trackingsystem.employeeservice.model.EmployeeWork;
import com.trackingsystem.employeeservice.service.EmployeeAllServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeWorkController {

    @Autowired
    private EmployeeAllServices employeeService;

    @PostMapping("/{id}/work")
    public ResponseEntity<EmployeeWork> saveEmployeeWork(@PathVariable Long id, @RequestBody EmployeeWork employeeWork) {
       return new ResponseEntity<>(employeeService.saveEmployeeWork(id, employeeWork), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EmployeeWork>> getWork(@PathVariable Long id) {

        return new ResponseEntity<>(employeeService.getWork(id),HttpStatus.OK);
    }


    @GetMapping("/employeeAllID")
    public ResponseEntity<List<Employee>> getEmployeeAllID() {

        return new ResponseEntity<>(employeeService.getEmployeeAllID(),HttpStatus.OK);
    }
}
