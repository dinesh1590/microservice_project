package com.trackingsystem.departmentservice.service;

import com.trackingsystem.departmentservice.client.EmployeeClient;
import com.trackingsystem.departmentservice.entity.EmployeeEntity;
import com.trackingsystem.departmentservice.model.Employee;
import com.trackingsystem.departmentservice.model.EmployeeWithWork;
import com.trackingsystem.departmentservice.model.EmployeeWorkEntity;
import com.trackingsystem.departmentservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentAllServices {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeClient employeeClient;

    public Optional<EmployeeEntity> getByEmployee(Long id) {

        Optional<EmployeeEntity> ee = employeeRepo.findById(id);

        return ee;
    }

    public String register(Employee employee) {

        String status = "successfully registered";
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmployeeId(employee.getEmployeeId());
        emp.setName(employee.getName());
        emp.setTeam(employee.getTeam());
        emp.setMail(employee.getMail());
        emp.setPhone(employee.getPhone());

        EmployeeEntity e = employeeRepo.save(emp);

        if (e.equals(null)) {
            return null;
        }

        return status;
    }

    public List<Employee> getAllEmployees() {

        List<EmployeeEntity> list = employeeRepo.findAll();
        return list.stream()
                .map(this::mapToEmployee)
                .collect(Collectors.toList());
    }

    public Employee mapToEmployee(EmployeeEntity entity) {
        Employee employee = new Employee();
        employee.setEmployeeId(entity.getEmployeeId());
        employee.setName(entity.getName());
        employee.setTeam(entity.getTeam());
        employee.setMail(entity.getMail());
        employee.setPhone(entity.getPhone());
        return employee;
    }

    public List<EmployeeWithWork> getAllEmployeesWithWork() {
        List<EmployeeEntity> list = employeeRepo.findAll();

        List<Employee> emp = list.stream()
                .map(this::mapToEmployee)
                .collect(Collectors.toList());

        List<EmployeeWithWork> employeesWithWork = new ArrayList<>();
        for (Employee employee : emp) {
            ResponseEntity<List<EmployeeWorkEntity>> empWorkResponse = employeeClient.getWork((long) employee.getEmployeeId());

            if(empWorkResponse != null) {
                List<EmployeeWorkEntity> empWorkList = empWorkResponse.getBody();
                employeesWithWork.add(mapEmployeeWorkEntityToEmployeeWork(empWorkList, employee));
            }
        }
            return employeesWithWork;
    }
        private EmployeeWithWork mapEmployeeWorkEntityToEmployeeWork(List<EmployeeWorkEntity> entity, Employee employee){
            EmployeeWithWork work = new EmployeeWithWork();

            if(employee != null)
             work.setEmployee(employee);

            if(entity != null)
             work.setWork(entity);

            return work;
        }

    public List<EmployeeWithWork> getByTeamAndToday(String team, LocalDate date) {

        List<EmployeeEntity> list = employeeRepo.findAll();

        List<Employee> emp = list.stream()
                .map(this::mapToEmployee)
                .collect(Collectors.toList());

        List<EmployeeWithWork> employeesWithWork = new ArrayList<>();

        for (Employee employee : emp) {

            ResponseEntity<List<EmployeeWorkEntity>> empWorkResponse = null;


           if(employee.getTeam().equals(team)) {
               empWorkResponse = employeeClient.getWork((long) employee.getEmployeeId());

               if (empWorkResponse != null) {
                   List<EmployeeWorkEntity> empWorkList = empWorkResponse.getBody();

                   List<EmployeeWorkEntity> filteredList = empWorkList.stream()
                           .filter(emps -> emps.workDate().equals(date)) // Filter based on the date
                           .collect(Collectors.toList());
                   employeesWithWork.add(mapEmployeeWorkEntityToEmployeeWork(filteredList, employee));
               }
           }
        }
        return employeesWithWork;
    }

    public List<EmployeeWithWork> getAllByDate(LocalDate date) {

        List<EmployeeEntity> list = employeeRepo.findAll();

        List<Employee> emp = list.stream()
                .map(this::mapToEmployee)
                .collect(Collectors.toList());

        List<EmployeeWithWork> employeesWithWork = new ArrayList<>();

        for (Employee employee : emp) {

            ResponseEntity<List<EmployeeWorkEntity>> empWorkResponse = null;
                empWorkResponse = employeeClient.getWork((long) employee.getEmployeeId());
                if (empWorkResponse != null) {
                    List<EmployeeWorkEntity> empWorkList = empWorkResponse.getBody();

                    List<EmployeeWorkEntity> filteredList = empWorkList.stream()
                            .filter(emps -> emps != null && emps.workDate() != null && emps.workDate().equals(date))
                            .collect(Collectors.toList());

                    if (filteredList != null && !filteredList.isEmpty() && employee != null)
                     employeesWithWork.add(mapEmployeeWorkEntityToEmployeeWork(filteredList, employee));

                }
            }
        return employeesWithWork;
    }
}
