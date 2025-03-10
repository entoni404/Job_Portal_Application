package al.job.portal.domain.repository;

import al.job.portal.domain.model.entity.Resume;
import al.job.portal.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByJobSeeker(User jobSeeker);
}
