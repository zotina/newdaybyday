package mg.itu.model.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.Pagination;

import java.util.List;

public class InvoiceData {
    @JsonProperty("invoices")
    private List<Invoice> invoices;

    @JsonProperty("pagination")
    private Pagination pagination;

    
    public InvoiceData(Invoice invoice) {
        this.invoices = List.of(invoice);
        this.pagination = null;
    }

    
    public InvoiceData(List<Invoice> invoices, Pagination pagination) {
        this.invoices = invoices;
        this.pagination = pagination;
    }

    public InvoiceData() {}

    
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}