package org.spring_web.jobportalapplication.model.mapper;

import org.spring_web.jobportalapplication.model.entity.Review;
import org.spring_web.jobportalapplication.model.resource.ReviewResource;
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

}
