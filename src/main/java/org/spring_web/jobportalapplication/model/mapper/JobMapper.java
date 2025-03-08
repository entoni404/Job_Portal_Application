package org.spring_web.jobportalapplication.model.mapper;

import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.resource.JobResource;
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
}
