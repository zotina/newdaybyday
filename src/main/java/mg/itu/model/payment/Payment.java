package mg.itu.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.RelatedEntity;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Payment {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("invoice")
    private RelatedEntity invoice;

    
    public Payment() {}

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public RelatedEntity getInvoice() { return invoice; }
    public void setInvoice(RelatedEntity invoice) { this.invoice = invoice; }
}