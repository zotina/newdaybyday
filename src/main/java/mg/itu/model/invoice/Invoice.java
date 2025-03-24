package mg.itu.model.invoice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.RelatedEntity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Invoice {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("client")
    private RelatedEntity client;

    @JsonProperty("invoice_lines")
    private List<RelatedEntity> invoiceLines;

    @JsonProperty("offer")
    private RelatedEntity offer;

    @JsonProperty("source")
    private RelatedEntity source;

    @JsonProperty("payments")
    private List<RelatedEntity> payments;

    
    public Invoice() {}

    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public RelatedEntity getClient() { return client; }
    public void setClient(RelatedEntity client) { this.client = client; }
    public List<RelatedEntity> getInvoiceLines() { return invoiceLines; }
    public void setInvoiceLines(List<RelatedEntity> invoiceLines) { this.invoiceLines = invoiceLines; }
    public RelatedEntity getOffer() { return offer; }
    public void setOffer(RelatedEntity offer) { this.offer = offer; }
    public RelatedEntity getSource() { return source; }
    public void setSource(RelatedEntity source) { this.source = source; }
    public List<RelatedEntity> getPayments() { return payments; }
    public void setPayments(List<RelatedEntity> payments) { this.payments = payments; }
}