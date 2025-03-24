package mg.itu.model.chart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientOverview {
    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("client_id")
    private Integer clientId;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("total_invoices")
    private Integer totalInvoices;

    @JsonProperty("total_invoiced_amount")
    private Double totalInvoicedAmount;

    @JsonProperty("total_paid_amount")
    private Double totalPaidAmount;

    @JsonProperty("outstanding_amount")
    private Double outstandingAmount;

    
    public ClientOverview() {}

    
    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public Integer getTotalInvoices() { return totalInvoices; }
    public void setTotalInvoices(Integer totalInvoices) { this.totalInvoices = totalInvoices; }
    public Double getTotalInvoicedAmount() { return totalInvoicedAmount; }
    public void setTotalInvoicedAmount(Double totalInvoicedAmount) { this.totalInvoicedAmount = totalInvoicedAmount; }
    public Double getTotalPaidAmount() { return totalPaidAmount; }
    public void setTotalPaidAmount(Double totalPaidAmount) { this.totalPaidAmount = totalPaidAmount; }
    public Double getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(Double outstandingAmount) { this.outstandingAmount = outstandingAmount; }
}