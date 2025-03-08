package org.spring_web.jobportalapplication.repository;

import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.entity.Review;
import org.spring_web.jobportalapplication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByJobAndEmployer(Job job, User employer);


    Page<Review> findByJobAndRatingGreaterThanEqual(Job job, int rating, Pageable pageable);


}
