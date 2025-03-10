package al.job.portal.domain.service;

import al.job.portal.domain.model.specifications.ReviewSpecification;
import al.job.portal.domain.repository.JobRepository;
import al.job.portal.shared.exceptions.ResourceNotFoundException;
import al.job.portal.shared.util.ErrorMessage;
import jakarta.transaction.Transactional;
import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.Review;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.mapper.ReviewMapper;
import al.job.portal.domain.model.resource.ReviewResource;
import al.job.portal.domain.repository.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private final JobRepository jobRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public ReviewService(
            JobRepository jobRepository,
            ReviewMapper reviewMapper,
            ReviewRepository reviewRepository
    ) {
        this.jobRepository = jobRepository;
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review createReview(ReviewResource reviewResource, User employer, Long jobId) {
        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorMessage.REVIEW_NOT_FOUND.getValue()));

        Review review = reviewMapper.toEntity(reviewResource);
        
        review.setEmployer(employer);
        review.setJob(job);

        reviewRepository.save(review);

        return review;
    }

    public Page<ReviewResource> getReviewByRating(int rating, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        ReviewSpecification specification = new ReviewSpecification()
                .withRating(rating);

        Page<Review> reviewPage = reviewRepository.findAll(specification, pageable);

        return reviewPage.map(reviewMapper::toDTO);
    }


}
