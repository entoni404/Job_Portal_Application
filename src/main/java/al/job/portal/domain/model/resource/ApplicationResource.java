package al.job.portal.domain.model.resource;

import al.job.portal.domain.model.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResource(
        Long jobSeekerId,
        Long jobId,
        ApplicationStatus applicationStatus,
        String coverLetter,
        LocalDateTime applicationDate) {
}
