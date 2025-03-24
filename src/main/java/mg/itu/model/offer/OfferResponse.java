package mg.itu.model.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfferResponse {
    @JsonProperty("data")
    private OfferData data;

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    
    public OfferResponse() {}

    
    public OfferData getData() {
        return data;
    }

    public void setData(OfferData data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


}