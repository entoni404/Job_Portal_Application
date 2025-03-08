package org.spring_web.jobportalapplication.model.resource;

import org.spring_web.jobportalapplication.model.enums.JobEntryLevel;

import java.time.LocalDateTime;

public record JobResource(
        String jobTitle, String jobDescription, String jobLocation, String jobFunction, String workingTime,
        LocalDateTime publishingDate,LocalDateTime applicationDeadline,  JobEntryLevel entryLevel) {
}
