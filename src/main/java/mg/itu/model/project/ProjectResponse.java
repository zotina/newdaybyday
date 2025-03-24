package mg.itu.model.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.modelMere.ModelMere;

import java.util.List;

public class ProjectResponse extends ModelMere {
    @JsonProperty("data")
    private List<Project> data;

    
    public ProjectResponse() {
        super();
    }

    public ProjectResponse(boolean success, String message, List<Project> data) {
        super(success, message);
        this.data = data;
    }

    
    public List<Project> getData() {
        return data;
    }

    public void setData(List<Project> data) {
        this.data = data;
    }
}