package mg.itu.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("vat")
    private String vat;

    @JsonProperty("address")
    private String address;

    public Client() {}

    public Client(String externalId, String companyName, String vat, String address) {
        this.externalId = externalId;
        this.companyName = companyName;
        this.vat = vat;
        this.address = address;
    }

    public String getExternalId() { return externalId; }
    public void setExternalId(String externalId) { this.externalId = externalId; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getVat() { return vat; }
    public void setVat(String vat) { this.vat = vat; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}