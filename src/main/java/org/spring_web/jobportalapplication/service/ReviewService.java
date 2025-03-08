package org.spring_web.jobportalapplication.service;

import jakarta.transaction.Transactional;
import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.entity.Review;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.UserRole;
import org.spring_web.jobportalapplication.model.mapper.ReviewMapper;
import org.spring_web.jobportalapplication.model.resource.ReviewResource;
import org.spring_web.jobportalapplication.repository.ReviewRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

        @Transactional
        public ReviewResource addReview(Review review, User employer){
            if(employer.getUserRole() != UserRole.EMPLOYER){
                throw new AccessDeniedException("You do not have permission to add review");
            }
            Optional<Review> existingReview = reviewRepository.findByJobAndEmployer(review.getJob(), employer);
            if(existingReview.isPresent()){
                throw new AccessDeniedException("A review for this job already exists");
            }

            return reviewMapper.toDTO(reviewRepository.save(review));
        }

        public Page<ReviewResource> getReviewByRating(Job job, int rating, Pageable pageable){
            if(job.getEmployer().getUserRole() != UserRole.EMPLOYER){
                throw new AccessDeniedException("You do not have permission");
            }
            Page<Review> reviewPage = reviewRepository.findByJobAndRatingGreaterThanEqual(
                    job, rating, pageable);

            return reviewPage.map(reviewMapper::toDTO);
    }


}
