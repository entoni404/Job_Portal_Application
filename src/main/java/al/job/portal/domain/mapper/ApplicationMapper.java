package al.job.portal.domain.mapper;

import al.job.portal.domain.model.entity.Application;
import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.resource.ApplicationResource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResource toDTO(Application application) {
        return new ApplicationResource(
                application.getJobSeeker().getUserId(),
                application.getJob().getJobId(),
                application.getApplicationStatus(),
                application.getCoverLetter(),
                application.getApplicationDate()
        );
    }


    public Application toEntity(ApplicationResource applicationResource) {
        if (applicationResource == null) {
            return null;
        }

        User jobSeeker = User.builder()
                .userId(applicationResource.jobSeekerId())
                .build();

        Job job = Job.builder()
                .jobId(applicationResource.jobId())
                .build();

        return Application.builder()
                .jobSeeker(jobSeeker)
                .job(job)
                .applicationStatus(applicationResource.applicationStatus())
                .coverLetter(applicationResource.coverLetter())
                .applicationDate(applicationResource.applicationDate())
                .build();
    }



}
