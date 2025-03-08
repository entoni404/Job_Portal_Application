package org.spring_web.jobportalapplication.repository;

import org.spring_web.jobportalapplication.model.entity.Resume;
import org.spring_web.jobportalapplication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByJobSeeker(User jobSeeker);
}
