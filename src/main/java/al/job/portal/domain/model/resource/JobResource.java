package al.job.portal.domain.model.resource;

import al.job.portal.domain.model.enums.JobEntryLevel;

import java.time.LocalDateTime;

public record JobResource(
        String jobTitle,
        String jobDescription,
        String jobLocation,
        String jobFunction,
        String workingTime,
        LocalDateTime publishingDate,
        LocalDateTime applicationDeadline,
        JobEntryLevel entryLevel
) {}
