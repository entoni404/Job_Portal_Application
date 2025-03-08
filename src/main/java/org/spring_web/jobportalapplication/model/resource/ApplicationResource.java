package org.spring_web.jobportalapplication.model.resource;

import org.spring_web.jobportalapplication.model.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResource(Long jobSeekerId, Long jobId, ApplicationStatus applicationStatus,
                                  String coverLetter, LocalDateTime applicationDate) {
}
