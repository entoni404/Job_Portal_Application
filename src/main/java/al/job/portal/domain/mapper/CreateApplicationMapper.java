package al.job.portal.domain.mapper;

import al.job.portal.domain.model.entity.Application;
import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.resource.CreateApplicationResource;
import org.springframework.stereotype.Component;

@Component
public class CreateApplicationMapper {

    public CreateApplicationResource toDTO(Application application) {
        return new CreateApplicationResource(
                application.getJobSeeker().getUserId(),
                application.getJob().getJobId(),
                application.getCoverLetter()
        );
    }

    public Application toEntity(CreateApplicationResource applicationResource) {
        if (applicationResource == null){
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
                .coverLetter(applicationResource.coverLetter())
                .build();
    }
    
}
