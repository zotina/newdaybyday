package mg.itu.model.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskStatus {
    @JsonProperty("title")
    private String title;

    @JsonProperty("color")
    private String color;

    
    public TaskStatus() {}

    public TaskStatus(String title, String color) {
        this.title = title; 
        this.color = color;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}