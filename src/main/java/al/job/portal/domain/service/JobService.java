package al.job.portal.domain.service;

import al.job.portal.domain.model.specifications.JobSpecification;
import al.job.portal.shared.exceptions.ResourceNotFoundException;
import al.job.portal.shared.util.ErrorMessage;
import jakarta.transaction.Transactional;
import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.mapper.JobMapper;
import al.job.portal.domain.model.resource.JobResource;
import al.job.portal.domain.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Job createJob(JobResource jobResource, User currentUser) {
        Job job = jobMapper.toEntity(jobResource);
        job.setEmployer(currentUser);

        jobRepository.save(job);

        return job;
    }

    public JobResource getJob(Long id) {
        Job job = jobRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.JOB_NOT_FOUND.getValue()));

        return jobMapper.toDTO(job);
    }

    public Page<JobResource> searchJob(String title, String location, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        JobSpecification specification = new JobSpecification()
                .withJobTitle(title)
                .withJobLocation(location);

        Page<Job> jobPage = jobRepository.findAll(specification, pageable);

        return jobPage.map(jobMapper::toDTO);
    }


    public Page<JobResource> searchByEmployer(String title, String location, User employer, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        JobSpecification specification = new JobSpecification()
                .withJobTitle(title)
                .withJobLocation(location)
                .withEmployer(employer);

        Page<Job> jobPage = jobRepository.findAll(specification, pageable);

        return jobPage.map(jobMapper::toDTO);

    }


}
