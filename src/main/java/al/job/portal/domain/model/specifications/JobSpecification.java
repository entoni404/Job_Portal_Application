package al.job.portal.domain.model.specifications;

import al.job.portal.domain.model.entity.Job;
import al.job.portal.domain.model.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification implements Specification<Job> {
    private String jobTitle;
    private String jobLocation;
    private User employer;

    public JobSpecification withJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;

        return this;
    }

    public JobSpecification withJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;

        return this;
    }

    public JobSpecification withEmployer(User employer) {
        this.employer = employer;

        return this;
    }

    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (jobTitle != null && !jobTitle.trim().isEmpty()) {
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("jobTitle")), "%" + jobTitle.toLowerCase() + "%"));
        }

        if (jobLocation != null && !jobLocation.trim().isEmpty()) {
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("jobLocation")), "%" + jobLocation.toLowerCase() + "%"));
        }

        if (employer != null) {
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.equal(root.get("employer"), employer));
        }

        return predicate;
    }
}
