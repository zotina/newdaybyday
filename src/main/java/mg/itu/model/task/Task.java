package mg.itu.model.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("deadline")
    private String deadline;

    @JsonProperty("assigned_user")
    private String assignedUser;

    @JsonProperty("status")
    private TaskStatus status;

    @JsonProperty("client")
    private String client;

    // Constructeurs
    public Task() {}

    public Task(String externalId, String title, String createdAt, String deadline, String assignedUser, TaskStatus status, String client) {
        this.externalId = externalId;
        this.title = title;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.assignedUser = assignedUser;
        this.status = status;
        this.client = client;
    }

    // Getters et Setters
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}