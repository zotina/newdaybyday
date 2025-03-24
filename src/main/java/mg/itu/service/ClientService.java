package mg.itu.service;

import mg.itu.model.client.Client;
import mg.itu.model.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;
 
    @Value("${api.base-url}")
    private String baseApiUrl;

    public List<Client> getClients(String accessToken, int page, int perPage) throws Exception {
        String clientsUrl = baseApiUrl.trim() + "client?page=" + page + "&per_page=" + perPage;
    
        WebClient client = webClientBuilder.baseUrl(clientsUrl).build();
    
        ResponseEntity<ClientResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(ClientResponse.class)
                .block();
    
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            ClientResponse clientResponse = response.getBody();
            if (clientResponse != null && clientResponse.isSuccess()) {
                return clientResponse.getData();
            } else {
                throw new Exception("API returned unsuccessful response: " + clientResponse.getMessage());
            }
        } else {
            throw new Exception("Failed to fetch clients: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }
    
}