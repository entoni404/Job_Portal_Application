package al.job.portal.domain.model.specifications;

import al.job.portal.domain.model.entity.User;
import al.job.portal.domain.model.enums.UserRole;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {

    private UserRole userRole;
    private String firstName;

    public UserSpecification withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserSpecification withUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (firstName != null && !firstName.trim().isEmpty()) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("firstName")),
                            "%" + firstName.toLowerCase() + "%"
                    )
            );
        }

        if (userRole != null) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.equal(root.get("userRole"), userRole)
            );
        }

        return predicate;
    }
}