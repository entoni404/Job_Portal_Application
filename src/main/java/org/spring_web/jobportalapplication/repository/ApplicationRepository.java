package org.spring_web.jobportalapplication.repository;

import org.spring_web.jobportalapplication.model.entity.Application;
import org.spring_web.jobportalapplication.model.entity.Job;
import org.spring_web.jobportalapplication.model.entity.User;
import org.spring_web.jobportalapplication.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Page<Application> findByJobAndApplicationStatus(Job job, ApplicationStatus status, Pageable pageable);

    Page<Application> findByJobSeekerAndJob_JobTitleContainingIgnoreCaseOrJobSeekerAndApplicationStatus(
            User jobSeeker1, String jobTitle, User jobSeeker2, ApplicationStatus status, Pageable pageable
            );

}
