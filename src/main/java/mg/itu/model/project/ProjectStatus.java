package mg.itu.model.project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectStatus {
    @JsonProperty("title")
    private String title;

    @JsonProperty("color")
    private String color;

    public ProjectStatus() {}

    public ProjectStatus(String title, String color) {
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