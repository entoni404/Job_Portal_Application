package org.spring_web.jobportalapplication.repository;

import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByJobTitleContainingIgnoreCaseOrJobLocationContainingIgnoreCase(
            String jobTitle, String jobLocation, Pageable pageable);

    Page<Job> findByJobTitleContainingIgnoreCaseOrJobLocationContainingIgnoreCaseOrEmployerContainingIgnoreCase(
            String jobTitle, String jobLocation, User employer, Pageable pageable);

}
