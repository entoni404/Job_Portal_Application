package al.job.portal.domain.model.specifications;

import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.Review;
import al.job.portal.domain.model.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification implements Specification<Review> {
    private int rating;
    private Job job;
    private User employer;

    public ReviewSpecification withRating(int rating) {
        this.rating = rating;

        return this;
    }

    public ReviewSpecification withJob(Job job) {
        this.job = job;

        return this;
    }

    public ReviewSpecification withEmployer(User employer) {
        this.employer = employer;

        return this;
    }


    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (rating > 0) {
            predicate = criteriaBuilder.equal(root.get("rating"), rating);
        }

        if (employer != null) {
            predicate = criteriaBuilder.equal(root.get("employer"), employer);
        }

        if (job != null) {
            predicate = criteriaBuilder.equal(root.get("job"), job);
        }

        return predicate;
    }
}
