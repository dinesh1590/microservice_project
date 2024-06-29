package com.trackingsystem.employeeservice.client;

import com.trackingsystem.employeeservice.config.DepartmentConfig;
import com.trackingsystem.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@Component
public class DepartmentClient {


    @Autowired
    private DepartmentConfig template;

    public List<Employee> getEmployeeId() {
        ResponseEntity<List<Employee>> responseEntity = template.template().exchange(
                "http://DEPARTMENT-SERVICE/department/getAllEmployees",
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );

        return responseEntity.getBody();
    }
}
