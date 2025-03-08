package org.spring_web.jobportalapplication.model.mapper;

import org.spring_web.jobportalapplication.model.entity.Resume;
import org.spring_web.jobportalapplication.model.resource.ResumeResource;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper {

    public ResumeResource toDTO(Resume resume) {
        return new ResumeResource(
                resume.getUploadDate(),
                resume.getTitle(),
                resume.getResumePath()
        );
    }

}
