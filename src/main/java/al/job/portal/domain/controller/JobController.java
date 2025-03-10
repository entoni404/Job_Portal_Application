package al.job.portal.domain.controller;

import al.job.portal.domain.mapper.JobMapper;
import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.resource.JobResource;
import al.job.portal.domain.repository.UserRepository;
import al.job.portal.domain.service.JobService;
import al.job.portal.shared.exceptions.ResourceNotFoundException;
import al.job.portal.shared.util.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobMapper jobMapper;
    private final JobService jobService;
    private final UserRepository userRepository;

    public JobController(
            JobMapper jobMapper,
            JobService jobService,
            UserRepository userRepository
    ) {
        this.jobMapper = jobMapper;
        this.jobService = jobService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResource> getJob(@PathVariable Long id) {
        JobResource job = jobService.getJob(id);

        return ResponseEntity.ok(job);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<Page<JobResource>> searchJob(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<JobResource> jobPage = jobService.searchJob(title, location, page, size);

        return ResponseEntity.ok(jobPage);
    }

    @GetMapping("/search/employer")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<Page<JobResource>> searchByEmployer(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam Long employerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        User employer = userRepository
                .findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND.getValue()));

        Page<JobResource> jobPage = jobService.searchByEmployer(title, location, employer, page, size);

        return ResponseEntity.ok(jobPage);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<JobResource> createJob(
            @RequestBody JobResource jobResource,
            @AuthenticationPrincipal User currentUser
    ) {
        Job job = jobService.createJob(jobResource, currentUser);

        return ResponseEntity
                .created(URI.create("/jobs/" + job.getJobId()))
                .body(jobMapper.toDTO(job));
    }
}
