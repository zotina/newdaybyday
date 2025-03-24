package mg.itu.model.modelMere;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelMere {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;

    
    public ModelMere() {
    }

    public ModelMere(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}