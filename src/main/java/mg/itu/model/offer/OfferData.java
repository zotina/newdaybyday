package mg.itu.model.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

import mg.itu.model.Pagination;

import java.util.List;

public class OfferData {
    @JsonProperty("offers")
    private List<Offer> offers;

    @JsonProperty("pagination")
    private Pagination pagination;

    // Pour une offre unique (pas de pagination)
    public OfferData(Offer offer) {
        this.offers = List.of(offer);
        this.pagination = null;
    }

    // Pour la liste paginée
    public OfferData(List<Offer> offers, Pagination pagination) {
        this.offers = offers;
        this.pagination = pagination;
    }

    public OfferData() {}

    // Getters et Setters
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}