package mg.itu.model.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceStatus {
    @JsonProperty("status_name")
    private String statusName;

    @JsonProperty("invoice_count")
    private Integer invoiceCount;

    @JsonProperty("percentage")
    private Double percentage;

    // Constructeurs
    public InvoiceStatus() {}

    // Getters et Setters
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    public Integer getInvoiceCount() { return invoiceCount; }
    public void setInvoiceCount(Integer invoiceCount) { this.invoiceCount = invoiceCount; }
    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }
}