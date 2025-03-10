package al.job.portal.domain.mapper;

import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.Review;
import al.job.portal.domain.model.resource.ReviewResource;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResource toDTO(Review review) {
        return new ReviewResource(
            review.getRating(),
            review.getComment(),
            review.getCreatedDate(),
            review.getJob().getJobId()
        );
    }

    public Review toEntity(ReviewResource reviewResource) {
        if (reviewResource == null) {
            return null;
        }

        Job job = Job.builder()
                .jobId(reviewResource.jobId())
                .build();

        return Review.builder()
                .rating(reviewResource.rating())
                .comment(reviewResource.comment())
                .createdDate(reviewResource.createdDate())
                .job(job)
                .build();
    }

}
