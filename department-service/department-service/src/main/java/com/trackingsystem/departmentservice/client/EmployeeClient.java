package com.trackingsystem.departmentservice.client;

import com.trackingsystem.departmentservice.model.EmployeeWorkEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

    @HttpExchange
    public interface EmployeeClient {

        @GetExchange("/employees/{id}")
        ResponseEntity<List<EmployeeWorkEntity>> getWork(@PathVariable Long id);

        @GetExchange("/employees/{id}")
        ResponseEntity<List<EmployeeWorkEntity>> getWorkById(@PathVariable Long id);

    }

