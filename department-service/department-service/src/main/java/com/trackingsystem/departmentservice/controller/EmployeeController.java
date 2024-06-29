package com.trackingsystem.departmentservice.controller;

import com.trackingsystem.departmentservice.entity.EmployeeEntity;
import com.trackingsystem.departmentservice.model.Employee;
import com.trackingsystem.departmentservice.model.EmployeeWithWork;
import com.trackingsystem.departmentservice.service.DepartmentAllServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class EmployeeController {

    @Autowired
    private DepartmentAllServices employeeAllServices;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> getById(@PathVariable Long id){

        return new ResponseEntity<>(employeeAllServices.getByEmployee(id), HttpStatus.OK);

    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(){

        return new ResponseEntity<>(employeeAllServices.getAllEmployees(), HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<String> registerNewUserAccount(@RequestBody Employee sign) {

        String output = employeeAllServices.register(sign);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/getAllEmployeesWithWork")
    public ResponseEntity<List<EmployeeWithWork>> getAllEmployeesWithWork(){

        return new ResponseEntity<>(employeeAllServices.getAllEmployeesWithWork(), HttpStatus.OK);

    }

    @GetMapping("/getByTeamAndToday")
    public ResponseEntity<List<EmployeeWithWork>> getByTeamAndToday( @RequestParam("team") String team,
                                                                     @RequestParam("date") LocalDate date){


        return new ResponseEntity<>(employeeAllServices.getByTeamAndToday(team,date), HttpStatus.OK);

    }

    @GetMapping("/getAllByDate")
    public ResponseEntity<List<EmployeeWithWork>> getAllByDate(@RequestParam("date") LocalDate date){


        return new ResponseEntity<>(employeeAllServices.getAllByDate(date), HttpStatus.OK);

    }

}
