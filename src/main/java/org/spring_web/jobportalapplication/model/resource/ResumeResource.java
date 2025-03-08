package org.spring_web.jobportalapplication.model.resource;

import java.time.LocalDateTime;

public record ResumeResource(LocalDateTime uploadDate, String title, String resumePath) {
}
