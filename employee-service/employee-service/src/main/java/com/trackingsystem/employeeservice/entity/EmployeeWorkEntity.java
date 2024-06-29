package com.trackingsystem.employeeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee_work")
public class EmployeeWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "attendance")
    private boolean attendance;

    @Column(name = "standup_meeting")
    private boolean standupMeeting;

    @Column(name = "jira_id")
    private String jiraId;

    @Column(name = "story_points")
    private Integer storyPoints;

    @Column(name = "blocker_flag_details", columnDefinition = "TEXT")
    private String blockerFlagDetails;

    @Column(name = "additional_comments", columnDefinition = "TEXT")
    private String additionalComments;

    @Column(name = "work_date")
    private LocalDate workDate;

}
