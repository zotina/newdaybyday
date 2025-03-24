package mg.itu.model.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.modelMere.ModelMere;

import java.util.List;

public class TaskResponse extends ModelMere {
    @JsonProperty("data")
    private List<Task> data;

    
    public TaskResponse() {
        super();
    } 

    public TaskResponse(boolean success, String message, List<Task> data) {
        super(success, message);
        this.data = data;
    }

    
    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }
}