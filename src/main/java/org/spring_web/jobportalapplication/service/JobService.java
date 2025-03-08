package org.spring_web.jobportalapplication.service;

import jakarta.transaction.Transactional;
import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.UserRole;
import org.spring_web.jobportalapplication.model.mapper.JobMapper;
import org.spring_web.jobportalapplication.model.resource.JobResource;
import org.spring_web.jobportalapplication.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Transactional
    public JobResource postJob(Job job, User employer) {
        if (employer.getUserRole() != UserRole.EMPLOYER) {
            throw new AccessDeniedException("Only EMPLOYEER users can post jobs.");
        }
        job.setEmployer(employer);
        return jobMapper.toDTO(jobRepository.save(job));
    }

    public Page<JobResource> getJobsByTitleOrLocation(String title, String location, Pageable pageable, User employer) {
        if (employer.getUserRole() != UserRole.EMPLOYER) {
            throw new AccessDeniedException("Only EMPLOYEER users can use this filter.");
        }
        Page<Job> jobPage = jobRepository.findByJobTitleContainingIgnoreCaseOrJobLocationContainingIgnoreCase(title, location, pageable);
        return jobPage.map(jobMapper::toDTO);
    }


    public Page<JobResource> getJobsByTitleOrLocationOrEmployer(
            String title, String location, User employer, Pageable pageable) {
        if (employer.getUserRole() != UserRole.JOB_SEEKER) {
            throw new AccessDeniedException("Only JOB_SEEKER users can use this filter.");
        }
        Page<Job> jobPage = jobRepository.
                findByJobTitleContainingIgnoreCaseOrJobLocationContainingIgnoreCaseOrEmployerContainingIgnoreCase(
                        title, location, employer, pageable);

        return jobPage.map(jobMapper::toDTO);

    }


}
