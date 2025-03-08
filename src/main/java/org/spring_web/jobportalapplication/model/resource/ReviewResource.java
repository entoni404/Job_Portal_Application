package org.spring_web.jobportalapplication.model.resource;

import java.time.LocalDateTime;

public record ReviewResource(int rating, String comment, LocalDateTime createDate, Long jobId) {
}
