package mg.itu.model.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskStatus {
    @JsonProperty("status_name")
    private String statusName;

    @JsonProperty("task_count")
    private Integer taskCount;

    @JsonProperty("percentage")
    private Double percentage;

    // Constructeurs
    public TaskStatus() {}

    // Getters et Setters
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    public Integer getTaskCount() { return taskCount; }
    public void setTaskCount(Integer taskCount) { this.taskCount = taskCount; }
    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }
}