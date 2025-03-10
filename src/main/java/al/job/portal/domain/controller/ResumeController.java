package al.job.portal.domain.controller;

import al.job.portal.domain.mapper.ResumeMapper;
import al.job.portal.domain.model.entity.Resume;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.resource.ResumeResource;
import al.job.portal.domain.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/resumes")
public class ResumeController {
    private final ResumeService resumeService;
    private final ResumeMapper resumeMapper;

    public ResumeController(ResumeService resumeService, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.resumeMapper = resumeMapper;
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<ResumeResource> upload(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal User currentUser
    ) throws IOException {
        Resume resume = resumeService.upload(file, title, currentUser);

        return ResponseEntity
                .created(URI.create("/resumes/" + resume.getResumeId()))
                .body(resumeMapper.toDTO(resume));
    }
}
