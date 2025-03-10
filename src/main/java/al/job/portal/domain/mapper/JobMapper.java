package al.job.portal.domain.mapper;

import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.resource.JobResource;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public JobResource toDTO(Job job) {
        return new JobResource(
                job.getJobTitle(),
                job.getJobDescription(),
                job.getJobLocation(),
                job.getJobFunction(),
                job.getWorkingTime(),
                job.getPublishingDate(),
                job.getApplicationDeadline(),
                job.getJobEntryLevel()
        );
    }

    public Job toEntity(JobResource resource) {
        if (resource == null) {
            return null;
        }

        return Job.builder()
                .jobTitle(resource.jobTitle())
                .jobDescription(resource.jobDescription())
                .jobLocation(resource.jobLocation())
                .jobFunction(resource.jobFunction())
                .workingTime(resource.workingTime())
                .publishingDate(resource.publishingDate())
                .applicationDeadline(resource.applicationDeadline())
                .jobEntryLevel(resource.entryLevel())
                .build();
    }
}
