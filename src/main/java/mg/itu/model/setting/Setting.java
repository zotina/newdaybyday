package mg.itu.model.setting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Setting {
    @JsonProperty("remise")
    private Double remise;

    // Constructeurs
    public Setting() {}

    public Setting(Double remise) {
        this.remise = remise;
    }

    // Getters et Setters
    public Double getRemise() { return remise; }
    public void setRemise(Double remise) { this.remise = remise; }
}