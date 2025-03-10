package al.job.portal.domain.model.resource;

import java.time.LocalDateTime;

public record ReviewResource(
        int rating,
        String comment,
        LocalDateTime createdDate,
        Long jobId
) {}
