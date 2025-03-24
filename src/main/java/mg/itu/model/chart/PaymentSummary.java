package mg.itu.model.chart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentSummary {
    @JsonProperty("payment_month")
    private String paymentMonth;

    @JsonProperty("payment_external_id")
    private String paymentExternalId;

    @JsonProperty("total_payments")
    private Integer totalPayments;

    @JsonProperty("total_invoices")
    private Integer totalInvoices;

    @JsonProperty("total_paid_amount")
    private Double totalPaidAmount;

    @JsonProperty("total_invoiced_amount")
    private Double totalInvoicedAmount;

    @JsonProperty("outstanding_amount")
    private Double outstandingAmount;

    
    public PaymentSummary() {}

    
    public String getPaymentMonth() { return paymentMonth; }
    public void setPaymentMonth(String paymentMonth) { this.paymentMonth = paymentMonth; }
    public String getPaymentExternalId() { return paymentExternalId; }
    public void setPaymentExternalId(String paymentExternalId) { this.paymentExternalId = paymentExternalId; }
    public Integer getTotalPayments() { return totalPayments; }
    public void setTotalPayments(Integer totalPayments) { this.totalPayments = totalPayments; }
    public Integer getTotalInvoices() { return totalInvoices; }
    public void setTotalInvoices(Integer totalInvoices) { this.totalInvoices = totalInvoices; }
    public Double getTotalPaidAmount() { return totalPaidAmount; }
    public void setTotalPaidAmount(Double totalPaidAmount) { this.totalPaidAmount = totalPaidAmount; }
    public Double getTotalInvoicedAmount() { return totalInvoicedAmount; }
    public void setTotalInvoicedAmount(Double totalInvoicedAmount) { this.totalInvoicedAmount = totalInvoicedAmount; }
    public Double getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(Double outstandingAmount) { this.outstandingAmount = outstandingAmount; }
}