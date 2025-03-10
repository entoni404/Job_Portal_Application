package al.job.portal.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import al.job.portal.domain.model.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User jobSeeker;

    @ManyToOne
    @JoinColumn(name ="job_id", nullable = false)
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column
    private ApplicationStatus applicationStatus;

    @Column
    private String coverLetter;

    @CreationTimestamp
    @Column
    private LocalDateTime applicationDate;

    public Application() {
    }

    public Application(
        Long id,
        User jobSeeker,
        Job job,
        ApplicationStatus applicationStatus,
        String coverLetter,
        LocalDateTime applicationDate
    ) {
        this.id = id;
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.applicationStatus = applicationStatus;
        this.coverLetter = coverLetter;
        this.applicationDate = applicationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(User jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus status) {
        this.applicationStatus = status;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long applicationId;
        private User jobSeeker;
        private Job job;
        private ApplicationStatus applicationStatus;
        private String coverLetter;
        private LocalDateTime applicationDate;

        public Builder applicationId(Long applicationId) {
            this.applicationId = applicationId;

            return this;
        }

        public Builder jobSeeker(User jobSeeker) {
            this.jobSeeker = jobSeeker;

            return this;
        }

        public Builder job(Job job) {
            this.job = job;

            return this;
        }

        public Builder applicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;

            return this;
        }

        public Builder coverLetter(String coverLetter) {
            this.coverLetter = coverLetter;

            return this;
        }

        public Builder applicationDate(LocalDateTime applicationDate) {
            this.applicationDate = applicationDate;

            return this;
        }

        public Application build(){
            return new Application(
                applicationId,
                jobSeeker,
                job,
                applicationStatus,
                coverLetter,
                applicationDate
            );
        }
    }
}
