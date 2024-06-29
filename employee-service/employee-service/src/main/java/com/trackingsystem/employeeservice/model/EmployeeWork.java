package com.trackingsystem.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWork {


    private boolean attendance;
    private boolean standUpMeeting;
    private String jiraID;
    private int storyPoints;
    private String blockerFlagDetails;
    private String additionalComments;
    private LocalDate workDate;

}
