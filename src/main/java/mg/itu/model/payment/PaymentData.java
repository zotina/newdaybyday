package mg.itu.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.Pagination;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class PaymentData {
    @JsonProperty("payments")
    private List<Payment> payments;

    @JsonProperty("pagination")
    private Pagination pagination;

    public PaymentData() {}

    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }
    public Pagination getPagination() { return pagination; }
    public void setPagination(Pagination pagination) { this.pagination = pagination; }
}