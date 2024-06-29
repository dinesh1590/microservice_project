package com.trackingsystem.employeeservice.repository;

import com.trackingsystem.employeeservice.entity.EmployeeWorkEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeWorkRepo  extends CrudRepository<EmployeeWorkEntity, Long> {

    @Query(value = "SELECT * FROM employee_work e WHERE e.employee_id = :id",nativeQuery = true)
    List<EmployeeWorkEntity> findAllFieldsById(@Param("id") Long id);

}
