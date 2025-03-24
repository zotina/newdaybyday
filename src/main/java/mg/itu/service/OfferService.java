package mg.itu.service;

import mg.itu.model.offer.Offer;
import mg.itu.model.offer.OfferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service  
public class OfferService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.base-url}")
    private String baseApiUrl;

    
    public List<Offer> getOffers(String accessToken, String externalId) throws Exception {
        String offersUrl = baseApiUrl.trim() + "offer" + (externalId != null ? "?external_id=" + externalId : "");

        WebClient client = webClientBuilder.baseUrl(offersUrl).build();

        ResponseEntity<OfferResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(OfferResponse.class)
                .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            OfferResponse offerResponse = response.getBody();
            if (offerResponse != null) {
                return offerResponse.getData().getOffers();
            } else {
                throw new Exception("API returned unsuccessful response: " + offerResponse.getMessage());
            }
        } else {
            throw new Exception("Failed to fetch offers: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }
}