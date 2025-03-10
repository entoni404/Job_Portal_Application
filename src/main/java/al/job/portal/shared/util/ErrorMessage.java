package al.job.portal.shared.util;

public enum ErrorMessage {
    USER_NOT_FOUND("User not found"),
    JOB_NOT_FOUND("Job not found"),
    REVIEW_NOT_FOUND("Review not found"),
    APPLICATION_NOT_FOUND("Application not found"),
    ACCESS_DENIED("Access denied");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
