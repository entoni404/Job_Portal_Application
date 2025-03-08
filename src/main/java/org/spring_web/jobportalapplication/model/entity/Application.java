package org.spring_web.jobportalapplication.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.spring_web.jobportalapplication.model.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Application(Long id, User jobSeeker, Job job, ApplicationStatus applicationStatus,
                       String coverLetter, LocalDateTime applicationDate) {
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

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", jobSeeker=" + jobSeeker +
                ", job=" + job +
                ", status=" + applicationStatus +
                ", coverLetter='" + coverLetter + '\'' +
                ", applicationDate=" + applicationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
