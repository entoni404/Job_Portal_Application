package al.job.portal.domain.mapper;

import al.job.portal.domain.model.entity.Resume;
import al.job.portal.domain.model.resource.ResumeResource;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper {

    public ResumeResource toDTO(Resume resume) {
        return new ResumeResource(
                null,
                resume.getUploadDate(),
                resume.getTitle(),
                resume.getResumePath()
        );
    }

    public Resume toEntity(ResumeResource resumeResource) {
        if (resumeResource == null) {
            return null;
        }
        return Resume.builder()
                .uploadDate(resumeResource.uploadDate())
                .title(resumeResource.title())
                .resumePath(resumeResource.resumePath())
                .build();
    }

}
