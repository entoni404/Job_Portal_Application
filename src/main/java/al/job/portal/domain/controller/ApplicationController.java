package al.job.portal.domain.controller;

import al.job.portal.domain.mapper.ApplicationMapper;
import al.job.portal.domain.model.entity.Application;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.enums.ApplicationStatus;
import al.job.portal.domain.model.resource.ApplicationResource;
import al.job.portal.domain.model.resource.CreateApplicationResource;
import al.job.portal.domain.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<ApplicationResource> submit(
            @RequestBody CreateApplicationResource applicationResource,
            @AuthenticationPrincipal User currentUser
    ) {
        Application application = applicationService.submit(applicationResource, currentUser);

        return ResponseEntity.created(URI.create("/applications/" + application.getId()))
                .body(applicationMapper.toDTO(application));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ApplicationResource>> searchApplications(
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ApplicationResource> applicationPage = applicationService.searchApplications(jobTitle, status, page, size);

        return ResponseEntity.ok(applicationPage);
    }


    @GetMapping("/status")
    public ResponseEntity<Page<ApplicationResource>> getApplicationsByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ApplicationStatus applicationStatus = ApplicationStatus.valueOf(status.toUpperCase());

        Page<ApplicationResource> applicationPage = applicationService.getApplicationByStatus(applicationStatus, page, size);

        return ResponseEntity.ok(applicationPage);
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<ApplicationResource> updateApplicationStatus(
            @PathVariable Long id,
            @RequestBody Map<String, ApplicationStatus> statusResource
    ) {
        ApplicationStatus status = statusResource.get("status");
        ApplicationResource application = applicationService.updateApplicationStatus(id, status);

        return ResponseEntity.ok(application);
    }
}