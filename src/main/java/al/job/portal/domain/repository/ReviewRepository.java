package al.job.portal.domain.repository;

import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.Review;
import al.job.portal.domain.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll(Specification<Review> spec, Pageable pageable);
}
