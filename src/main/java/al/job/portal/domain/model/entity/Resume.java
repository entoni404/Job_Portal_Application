package al.job.portal.domain.model.entity;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long resumeId;
        private User user;
        private LocalDateTime uploadDate;
        private String title;
        private String resumePath;

        public Builder resumeId(Long resumeId) {
            this.resumeId = resumeId;

            return this;
        }

        public Builder user(User user) {
            this.user = user;

            return this;
        }

        public Builder uploadDate(LocalDateTime uploadDate) {
            this.uploadDate = uploadDate;

            return this;
        }

        public Builder title(String title) {
            this.title = title;

            return this;
        }

        public Builder resumePath(String resumePath) {
            this.resumePath = resumePath;

            return this;
        }

        public Resume build() {
            return new Resume(
                resumeId,
                user,
                uploadDate,
                title,
                resumePath
            );
        }
    }
}
