package al.job.portal.domain.controller;

import al.job.portal.domain.mapper.ReviewMapper;
import al.job.portal.domain.model.entity.Review;
import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.resource.ReviewResource;
import al.job.portal.domain.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<ReviewResource> createReview(
            @RequestBody ReviewResource reviewResource,
            @RequestParam Long jobId,
            @AuthenticationPrincipal User currentUser
    ) {
        Review review = reviewService.createReview(reviewResource, currentUser, jobId);

        return ResponseEntity.created(URI.create("/reviews/" + review.getId()))
                .body(reviewMapper.toDTO(review));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ReviewResource>> getReviewsByRating(
            @RequestParam int rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ReviewResource> reviewPage = reviewService.getReviewByRating(rating, page, size);

        return ResponseEntity.ok(reviewPage);
    }
}
