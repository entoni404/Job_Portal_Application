package org.spring_web.jobportalapplication.model.mapper;

import org.spring_web.jobportalapplication.model.entity.Application;
import org.spring_web.jobportalapplication.model.resource.ApplicationResource;
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



}
