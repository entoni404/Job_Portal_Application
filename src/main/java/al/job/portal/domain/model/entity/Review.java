package al.job.portal.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int rating;

    @Column
    private String comment;

    @CreationTimestamp
    @Column
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;

    public Review() {
    }

    public Review(Long id, int rating, String comment, LocalDateTime createdDate, Job job, User employer) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.createdDate = createdDate;
        this.job = job;
        this.employer = employer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", job=" + job +
                ", employer=" + employer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private int rating;
        private String comment;
        private LocalDateTime createdDate;
        private Job job;
        private User employer;

        public Builder id(Long id) {
            this.id = id;

            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;

            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;

            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;

            return this;
        }

        public Builder job(Job job) {
            this.job = job;

            return this;
        }

        public Builder employer(User employer) {
            this.employer = employer;

            return this;
        }

        public Review build() {
            return new Review(
                id,
                rating,
                comment,
                createdDate,
                job,
                employer
            );
        }
    }
}
