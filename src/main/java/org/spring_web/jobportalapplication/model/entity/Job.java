package org.spring_web.jobportalapplication.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.spring_web.jobportalapplication.model.enums.JobEntryLevel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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


    public Job(Long jobId, String jobTitle, String jobDescription, String jobLocation,
               String jobFunction, String workingTime, LocalDateTime publishingDate,
               LocalDateTime applicationDeadline, JobEntryLevel jobEntryLevel,
               List<Application> applicationList, List<Review> reviewList, User employer) {
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

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                ", jobFunction='" + jobFunction + '\'' +
                ", workingTime='" + workingTime + '\'' +
                ", publishingDate=" + publishingDate +
                ", applicationDeadline=" + applicationDeadline +
                ", jobEntryLevel=" + jobEntryLevel +
                ", applicationList=" + applicationList +
                ", reviewList=" + reviewList +
                ", employer=" + employer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(jobId, job.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jobId);
    }
}
