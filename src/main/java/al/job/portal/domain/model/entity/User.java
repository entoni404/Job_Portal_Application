package al.job.portal.domain.model.entity;

import al.job.portal.domain.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column()
    private String password;

    @Column
    private String userLocation;

    @Column
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole userRole;

    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Resume resume;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Job> jobs;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    private User(
            Long userId,
            String firstName,
            String lastName,
            String email,
            String password,
            String userLocation,
            Long phoneNumber,
            UserRole userRole,
            Resume resume,
            List<Application> applications,
            List<Review> reviews,
            List<Job> jobs
    ) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userLocation = userLocation;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.resume = resume;
        this.applications = applications;
        this.reviews = reviews;
        this.jobs = jobs;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String userLocation;
        private Long phoneNumber;
        private UserRole userRole;
        private Resume resume;
        private List<Application> applications;
        private List<Review> reviews;
        private List<Job> jobs;

        public Builder userId(Long userId) {
            this.userId = userId;

            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;

            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;

            return this;
        }
        public Builder email(String email) {
            this.email = email;

            return this;
        }
        public Builder password(String password) {
            this.password = password;

            return this;
        }
        public Builder userLocation(String userLocation) {
            this.userLocation = userLocation;

            return this;
        }

        public Builder phoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;

            return this;
        }

        public Builder userRole(UserRole userRole) {
            this.userRole = userRole;

            return this;
        }

        public Builder resume(Resume resume) {
            this.resume = resume;

            return this;
        }

        public Builder applications(List<Application> applications) {
            this.applications = applications;

            return this;
        }

        public Builder reviews(List<Review> reviews) {
            this.reviews = reviews;

            return this;
        }

        public Builder jobs(List<Job> jobs) {
            this.jobs = jobs;

            return this;
        }

        public User build() {
            return new User(
                    userId,
                    firstName,
                    lastName,
                    email,
                    password,
                    userLocation,
                    phoneNumber,
                    userRole,
                    resume,
                    applications,
                    reviews,
                    jobs
            );
        }
    }
}
