package org.spring_web.jobportalapplication.service;

import jakarta.transaction.Transactional;
import org.spring_web.jobportalapplication.exceptions.ResourceNotFoundException;
import org.spring_web.jobportalapplication.model.entity.Application;
import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.ApplicationStatus;
import org.spring_web.jobportalapplication.model.enums.UserRole;
import org.spring_web.jobportalapplication.model.mapper.ApplicationMapper;
import org.spring_web.jobportalapplication.model.resource.ApplicationResource;
import org.spring_web.jobportalapplication.repository.ApplicationRepository;
import org.spring_web.jobportalapplication.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.jobRepository = jobRepository;
    }

    @Transactional
    public ApplicationResource applyForJob(Application application) {
        if(applicationRepository.existsById(application.getId())) {
            throw new IllegalArgumentException("Application with id " + application.getId() + " already exists");
        }else if(!jobRepository.existsById(application.getJob().getJobId())) {
            throw new IllegalArgumentException("Job with id " + application.getJob().getJobId() + " does not exist");
        }else if(application.getJobSeeker().getUserRole() != UserRole.JOB_SEEKER) {
            throw new AccessDeniedException("Only Job Seekers can apply for this application");
        }
            return applicationMapper.toDTO(applicationRepository.save(application));
    }

    public Page<ApplicationResource> getApplicationByTitleOrStatus(User jobSeeker, String jobTitle, ApplicationStatus applicationStatus, Pageable pageable) {
        Page<Application> applicationPage = applicationRepository.
                findByJobSeekerAndJob_JobTitleContainingIgnoreCaseOrJobSeekerAndApplicationStatus(
                        jobSeeker, jobTitle, jobSeeker, applicationStatus, pageable
                );

        return applicationPage.map(applicationMapper::toDTO);
    }


    public Page<ApplicationResource> getApplicationByStatus(Job job, ApplicationStatus applicationStatus, Pageable pageable) {
        Page<Application> applicationPage = applicationRepository.findByJobAndApplicationStatus(
                job, applicationStatus, pageable
        );

        return applicationPage.map(applicationMapper::toDTO);
    }


    @Transactional
    public ApplicationResource updateApplicationStatus(Long applicationId, ApplicationStatus applicationStatus, User employer) {
        if(!applicationRepository.existsById(applicationId)) {
            throw new IllegalArgumentException("Application with id " + applicationId + " does not exist");
        }
        if(employer.getUserRole() != UserRole.EMPLOYER){
            throw new AccessDeniedException("You are not an Employer");
        }
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()-> new ResourceNotFoundException("Application with id " + applicationId + " does not exist"));

        if(!application.getJob().getEmployer().equals(employer)){
            throw new AccessDeniedException("Employer does not own this job application");
        }
        application.setApplicationStatus(applicationStatus);

        return applicationMapper.toDTO(applicationRepository.save(application));

    }


}
