package al.job.portal.domain.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import al.job.portal.domain.model.enums.JobEntryLevel;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column
    private String jobTitle;

    @Column
    private String jobDescription;

    @Column
    private String jobLocation;

    @Column
    private String jobFunction;

    @Column
    private String workingTime;

    @CreationTimestamp
    @Column
    private LocalDateTime publishingDate;

    @Column
    private LocalDateTime applicationDeadline;

    @Enumerated(EnumType.STRING)
    @Column
    private JobEntryLevel jobEntryLevel;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applicationList;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Review> reviewList;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    public Job() {
    }

    public Job(
        Long jobId,
        String jobTitle,
        String jobDescription,
        String jobLocation,
        String jobFunction,
        String workingTime,
        LocalDateTime publishingDate,
        LocalDateTime applicationDeadline,
        JobEntryLevel jobEntryLevel,
        List<Application> applicationList,
        List<Review> reviewList,
        User employer
    ) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobFunction = jobFunction;
        this.workingTime = workingTime;
        this.publishingDate = publishingDate;
        this.applicationDeadline = applicationDeadline;
        this.jobEntryLevel = jobEntryLevel;
        this.applicationList = applicationList;
        this.reviewList = reviewList;
        this.employer = employer;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobFunction() {
        return jobFunction;
    }

    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public LocalDateTime getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDateTime publishingDate) {
        this.publishingDate = publishingDate;
    }

    public LocalDateTime getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDateTime applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public JobEntryLevel getJobEntryLevel() {
        return jobEntryLevel;
    }

    public void setJobEntryLevel(JobEntryLevel jobEntryLevel) {
        this.jobEntryLevel = jobEntryLevel;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long jobId;
        private String jobTitle;
        private String jobDescription;
        private String jobLocation;
        private String jobFunction;
        private String workingTime;
        private LocalDateTime publishingDate;
        private LocalDateTime applicationDeadline;
        private JobEntryLevel jobEntryLevel;
        private List<Application> applicationList;
        private List<Review> reviewList;
        private User employer;

        public Builder jobId(Long jobId) {
            this.jobId = jobId;

            return this;
        }

        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;

            return this;
        }

        public Builder jobDescription(String jobDescription) {
            this.jobDescription = jobDescription;

            return this;
        }

        public Builder jobLocation(String jobLocation) {
            this.jobLocation = jobLocation;
            return this;
        }

        public Builder jobFunction(String jobFunction) {
            this.jobFunction = jobFunction;

            return this;
        }

        public Builder workingTime(String workingTime) {
            this.workingTime = workingTime;

            return this;
        }

        public Builder publishingDate(LocalDateTime publishingDate) {
            this.publishingDate = publishingDate;

            return this;
        }

        public Builder applicationDeadline(LocalDateTime applicationDeadline) {
            this.applicationDeadline = applicationDeadline;

            return this;
        }

        public Builder jobEntryLevel(JobEntryLevel jobEntryLevel) {
            this.jobEntryLevel = jobEntryLevel;

            return this;
        }

        public Builder applicationList(List<Application> applicationList) {
            this.applicationList = applicationList;

            return this;
        }

        public Builder reviewList(List<Review> reviewList) {
            this.reviewList = reviewList;
            
            return this;
        }
        
        public Builder employer(User employer) {
            this.employer = employer;
            
            return this;
        }
        
        public Job build() {
            return new Job(
                jobId,
                jobTitle,
                jobDescription,
                jobLocation,
                jobFunction,
                workingTime,
                publishingDate,
                applicationDeadline,
                jobEntryLevel,
                applicationList,
                reviewList,
                employer
            );
        }
    }
}
