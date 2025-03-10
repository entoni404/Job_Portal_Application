package al.job.portal.domain.service;

import al.job.portal.domain.mapper.CreateApplicationMapper;
import al.job.portal.domain.model.resource.CreateApplicationResource;
import al.job.portal.domain.model.specifications.ApplicationSpecification;
import al.job.portal.shared.util.ErrorMessage;
import jakarta.transaction.Transactional;
import al.job.portal.shared.exceptions.ResourceNotFoundException;
import al.job.portal.domain.model.entity.Application;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.enums.ApplicationStatus;
import al.job.portal.domain.mapper.ApplicationMapper;
import al.job.portal.domain.model.resource.ApplicationResource;
import al.job.portal.domain.repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Application submit(CreateApplicationResource applicationResource, User currentUser) {
        Application application = createApplicationMapper.toEntity(applicationResource);

        application.setJobSeeker(currentUser);
        applicationRepository.save(application);

        return application;
    }

    public Page<ApplicationResource> searchApplications(String jobTitle, ApplicationStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        ApplicationSpecification specification = new ApplicationSpecification()
                .withApplicationStatus(status)
                .withJobTitle(jobTitle);

        Page<Application> applicationPage = applicationRepository.findAll(specification, pageable);

        return applicationPage.map(applicationMapper::toDTO);
    }

    public Page<ApplicationResource> getApplicationByStatus(ApplicationStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        ApplicationSpecification specification = new ApplicationSpecification()
                .withApplicationStatus(status);

        Page<Application> applicationPage = applicationRepository.findAll(specification, pageable);

        return applicationPage.map(applicationMapper::toDTO);
    }


    @Transactional
    public ApplicationResource updateApplicationStatus(Long id, ApplicationStatus status) {
        Application application = applicationRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.APPLICATION_NOT_FOUND.getValue()));

        application.setApplicationStatus(status);

        applicationRepository.save(application);

        return applicationMapper.toDTO(applicationRepository.save(application));

    }


}
