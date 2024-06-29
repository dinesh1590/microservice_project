package com.trackingsystem.departmentservice.model;

import java.time.LocalDate;
public record EmployeeWorkEntity(boolean attendance, boolean standUpMeeting, String jiraID, int storyPoints, String blockerFlagDetails, String additionalComments, LocalDate workDate) {


}
