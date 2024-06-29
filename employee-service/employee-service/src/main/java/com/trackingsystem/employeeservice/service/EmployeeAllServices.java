package com.trackingsystem.employeeservice.service;

import com.trackingsystem.employeeservice.client.DepartmentClient;
import com.trackingsystem.employeeservice.entity.EmployeeWorkEntity;
import com.trackingsystem.employeeservice.model.Employee;
import com.trackingsystem.employeeservice.model.EmployeeWork;
import com.trackingsystem.employeeservice.repository.EmployeeWorkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeAllServices {

    @Autowired
    private EmployeeWorkRepo employeeWorkRepo;

    public EmployeeWork saveEmployeeWork(Long id, EmployeeWork employeeWork) {

        EmployeeWorkEntity emp = new EmployeeWorkEntity();

        emp.setEmployeeId(id);
        emp.setAttendance(employeeWork.isAttendance());
        emp.setJiraId(employeeWork.getJiraID());
        emp.setStoryPoints(employeeWork.getStoryPoints());
        emp.setStandupMeeting(employeeWork.isStandUpMeeting());
        emp.setBlockerFlagDetails(employeeWork.getBlockerFlagDetails());
        emp.setWorkDate(employeeWork.getWorkDate());
        emp.setAdditionalComments(employeeWork.getAdditionalComments());

        EmployeeWorkEntity e = employeeWorkRepo.save(emp);

        if (e.equals(null)) {
            return null;
        }

        return mapEmployeeWorkEntityToEmployeeWork(e);

    }

    public List<EmployeeWork> getWork(Long id) {

        List<EmployeeWorkEntity> ss= employeeWorkRepo.findAllFieldsById(id);

        return ss.stream()
                .map(this::mapEmployeeWorkEntityToEmployeeWork)
                .collect(Collectors.toList());

    }

    private EmployeeWork mapEmployeeWorkEntityToEmployeeWork(EmployeeWorkEntity entity) {
        EmployeeWork work = new EmployeeWork();
        work.setAttendance(entity.isAttendance());
        work.setStandUpMeeting(entity.isStandupMeeting());
        work.setJiraID(entity.getJiraId());
        work.setStoryPoints(entity.getStoryPoints());
        work.setBlockerFlagDetails(entity.getBlockerFlagDetails());
        work.setAdditionalComments(entity.getAdditionalComments());
        work.setWorkDate(entity.getWorkDate());
        return work;
    }

    @Autowired
    private DepartmentClient dc;

    public List<Employee> getEmployeeAllID() {

        List<Employee> ss=  dc.getEmployeeId();

       return ss;
    }
}
