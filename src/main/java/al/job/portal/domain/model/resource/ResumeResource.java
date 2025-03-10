package al.job.portal.domain.model.resource;

import java.time.LocalDateTime;

public record ResumeResource(
        Long resumeId,
        LocalDateTime uploadDate,
        String title,
        String resumePath
) {}
