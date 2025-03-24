package mg.itu.model.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class DashboardResponse {
    @JsonProperty("datasheet")
    private Map<String, Map<String, Integer>> datasheet;
    
    @JsonProperty("total_tasks")
    private int totalTasks;  
    
    @JsonProperty("total_projects")
    private int totalProjects;
    
    @JsonProperty("total_clients")
    private int totalClients;
    
    @JsonProperty("offer")
    private int offer;
    
    @JsonProperty("invoices")
    private int invoices;
    
    @JsonProperty("payment")
    private double payment;
    
    @JsonProperty("payment_percentage")
    private double paymentPercentage;
    
    @JsonProperty("invoices_percentage")
    private double invoicesPercentage;
    
    @JsonProperty("offer_percentage")
    private double offerPercentage;
    
    @JsonProperty("total_tasks_percentage")
    private double totalTasksPercentage;
    
    @JsonProperty("total_projects_percentage")
    private double totalProjectsPercentage;
    
    @JsonProperty("total_clients_percentage")
    private double totalClientsPercentage;
    
    public int getOffer() {
        return offer;
    }
     
    public void setOffer(int offer) {
        this.offer = offer;
    }    
    
    public int getInvoices() {   
        return invoices;
    } 
    
    public void setInvoices(int invoices) {
        this.invoices = invoices;
    }
    
    public Map<String, Map<String, Integer>> getDatasheet() {
        return datasheet;
    }
    
    public void setDatasheet(Map<String, Map<String, Integer>> datasheet) {
        this.datasheet = datasheet;
    }
    
    public int getTotalTasks() {
        return totalTasks;
    }
    
    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }     
    
    public int getTotalProjects() {
        return totalProjects;
    }
    
    public void setTotalProjects(int totalProjects) {
        this.totalProjects = totalProjects;
    }
    
    public int getTotalClients() {
        return totalClients;
    }
       
    public void setTotalClients(int totalClients) {
        this.totalClients = totalClients;
    }
    
    public double getPaymentPercentage() {
        return paymentPercentage;
    }
   
    public void setPaymentPercentage(double paymentPercentage) {
        this.paymentPercentage = paymentPercentage;
    } 
    
    public double getInvoicesPercentage() {
        return invoicesPercentage;
    }
    
    public void setInvoicesPercentage(double invoicesPercentage) {
        this.invoicesPercentage = invoicesPercentage;
    }
    
    public double getOfferPercentage() {
        return offerPercentage;
    }
    
    public void setOfferPercentage(double offerPercentage) {
        this.offerPercentage = offerPercentage;
    }
       
    public double getTotalTasksPercentage() {
        return totalTasksPercentage;
    }
    
    public void setTotalTasksPercentage(double totalTasksPercentage) {
        this.totalTasksPercentage = totalTasksPercentage;
    } 
    
    public double getTotalProjectsPercentage() {
        return totalProjectsPercentage;
    }
    
    public void setTotalProjectsPercentage(double totalProjectsPercentage) {
        this.totalProjectsPercentage = totalProjectsPercentage;
    }
    
    public double getTotalClientsPercentage() {
        return totalClientsPercentage;
    }
    
    public void setTotalClientsPercentage(double totalClientsPercentage) {
        this.totalClientsPercentage = totalClientsPercentage;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "DashboardResponse [datasheet=" + datasheet + ", totalTasks=" + totalTasks + ", totalProjects="
                + totalProjects + ", totalClients=" + totalClients + ", offer=" + offer + ", invoices=" + invoices
                + ", payment=" + payment + ", paymentPercentage=" + paymentPercentage + ", invoicesPercentage="
                + invoicesPercentage + ", offerPercentage=" + offerPercentage + ", totalTasksPercentage="
                + totalTasksPercentage + ", totalProjectsPercentage=" + totalProjectsPercentage
                + ", totalClientsPercentage=" + totalClientsPercentage + "]";
    }
    
}