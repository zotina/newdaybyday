package mg.itu.model.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.RelatedEntity;

import java.util.List;

public class Offer {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("sent_at")
    private String sentAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("due_at")
    private String dueAt;

    @JsonProperty("client_id")
    private Integer clientId;

    @JsonProperty("source_id")
    private Integer sourceId;

    @JsonProperty("source_type")
    private String sourceType;

    @JsonProperty("invoice_lines")
    private List<RelatedEntity> invoiceLines;

    @JsonProperty("invoice")
    private RelatedEntity invoice;

    // Constructeurs
    public Offer() {}

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public List<RelatedEntity> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<RelatedEntity> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public RelatedEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(RelatedEntity invoice) {
        this.invoice = invoice;
    }
}