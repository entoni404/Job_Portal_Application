package al.job.portal.domain.service;

import jakarta.transaction.Transactional;
import al.job.portal.domain.model.entity.Resume;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Transactional
    public Resume upload(MultipartFile file, String title, User jobSeeker) throws IOException {
        Path directoryPath = Paths.get("uploads/resumes");

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filename = timestamp + "_" + file.getOriginalFilename();

        Path uploadPath = directoryPath.resolve(filename);

        Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

        Resume resume = resumeRepository.findByJobSeeker(jobSeeker);

        if (resume == null) {
            resume = Resume.builder()
                    .user(jobSeeker)
                    .build();
        }

        resume.setTitle(title);
        resume.setResumePath(uploadPath.toString());

        resumeRepository.save(resume);

        return resume;
    }
}
