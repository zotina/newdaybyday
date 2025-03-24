package mg.itu.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class PaymentResponse {
    @JsonProperty("data")
    private Object data; 

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    
    public PaymentResponse() {}

    
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}