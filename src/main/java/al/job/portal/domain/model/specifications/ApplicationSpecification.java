package al.job.portal.domain.model.specifications;

import al.job.portal.domain.model.entity.Application;
import al.job.portal.domain.model.enums.ApplicationStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ApplicationSpecification implements Specification<Application> {
    private ApplicationStatus status;
    private String jobTitle;

    public ApplicationSpecification withApplicationStatus(ApplicationStatus status) {
        this.status = status;

        return this;
    }

    public ApplicationSpecification withJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;

        return this;
    }

    @Override
    public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        if (status != null) {
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.equal(root.get("applicationStatus"), status));
        }

        if (jobTitle != null && !jobTitle.isEmpty()) {
            predicate = criteriaBuilder
                    .and(predicate, criteriaBuilder.equal(root.get("job").<String>get("jobTitle"), jobTitle));
        }

        return predicate;
    }
}
