package al.job.portal.domain.model.resource;

public record CreateApplicationResource(
        Long jobSeekerId,
        Long jobId,
        String coverLetter
) {}
