package mg.itu.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {
    @JsonProperty("total")
    private Integer total;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("last_page")
    private Integer lastPage;

    @JsonProperty("from")
    private Integer from;

    @JsonProperty("to")
    private Integer to;

    // Constructeurs
    public Pagination() {}

    // Getters et Setters
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}