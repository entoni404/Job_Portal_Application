package al.job.portal.domain.repository;

import al.job.portal.domain.model.entity.Application;
import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.enums.ApplicationStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findAll(Specification<Application> spec, Pageable pageable);
}
