package mg.itu.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.modelMere.ModelMere;

import java.util.List;

public class ClientResponse extends ModelMere {

    @JsonProperty("data")
    private List<Client> data;

    
    public ClientResponse() {
        super(); 
    }

    public ClientResponse(boolean success, String message, List<Client> data) {
        super(success, message); 
        this.data = data;
    }

    
    public List<Client> getData() {
        return data;
    }

    public void setData(List<Client> data) {
        this.data = data;
    }
}