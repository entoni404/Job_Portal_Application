package org.spring_web.jobportalapplication.service;

import jakarta.transaction.Transactional;
import org.spring_web.jobportalapplication.model.entity.Resume;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.UserRole;
import org.spring_web.jobportalapplication.model.mapper.ResumeMapper;
import org.spring_web.jobportalapplication.model.resource.ResumeResource;
import org.spring_web.jobportalapplication.repository.ResumeRepository;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    public ResumeService(ResumeRepository resumeRepository, ResumeMapper resumeMapper) {
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
    }


    @Transactional
    public ResumeResource uploadResume(MultipartFile file, String title, User jobSeeker) throws AccessDeniedException {
        if (jobSeeker.getUserRole() != UserRole.JOB_SEEKER) {
            throw new AccessDeniedException("You are not a Job Seeker");
        }

        String uploadDir = "uploads/resumes/";
        Path uploadPath = Paths.get(uploadDir);

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }

        String originalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = uploadPath.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName, e);
        }

        String storedPath = filePath.toString();

        Resume existingResume = resumeRepository.findByJobSeeker(jobSeeker);
        Resume resume;
        if (existingResume != null) {
            existingResume.setTitle(title);
            existingResume.setResumePath(storedPath);
            resume = resumeRepository.save(existingResume);
        } else {
            resume = new Resume();
            resume.setTitle(title);
            resume.setResumePath(storedPath);
            resume.setJobSeeker(jobSeeker);
            resume = resumeRepository.save(resume);
        }

        return resumeMapper.toDTO(resume);
    }

}
