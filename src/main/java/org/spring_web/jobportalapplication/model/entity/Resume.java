package org.spring_web.jobportalapplication.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

    @Entity
    @Table(name = "resumes")
    public class Resume {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long resumeId;

        @OneToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User jobSeeker;

        @CreationTimestamp
        @Column
        private LocalDateTime uploadDate;

        @Column
        private String title;

        @Column
        private String resumePath;

    public Resume() {
    }

    public Resume(Long resumeId, User user, LocalDateTime uploadDate, String title, String resumePath) {
        this.resumeId = resumeId;
        this.jobSeeker = user;
        this.uploadDate = uploadDate;
        this.title = title;
        this.resumePath = resumePath;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public User getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(User user) {
        this.jobSeeker = user;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "resumeId=" + resumeId +
                ", user=" + jobSeeker +
                ", uploadDate=" + uploadDate +
                ", title='" + title + '\'' +
                ", resumePath='" + resumePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(resumeId, resume.resumeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resumeId);
    }
}
