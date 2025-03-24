package mg.itu.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMonth {
    @JsonProperty("payment_month")
    private String paymentMonth;

    @JsonProperty("amount_total")
    private Double amountTotal;

    // Constructeurs
    public PaymentMonth() {}

    // Getters et Setters
    public String getPaymentMonth() { return paymentMonth; }
    public void setPaymentMonth(String paymentMonth) { this.paymentMonth = paymentMonth; }
    public Double getAmountTotal() { return amountTotal; }
    public void setAmountTotal(Double amountTotal) { this.amountTotal = amountTotal; }
}