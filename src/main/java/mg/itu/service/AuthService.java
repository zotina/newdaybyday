package mg.itu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import mg.itu.model.form.LoginRequest;
import mg.itu.model.form.LoginResponse;
import mg.itu.model.page.DashboardResponse;

@Service
public class AuthService { 

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.base-url}")
    private String baseApiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        String loginUrl = baseApiUrl.trim() + "auth/login";
        WebClient client = webClientBuilder.baseUrl(loginUrl).build();

        ResponseEntity<String> response = client.post()
            .header("Content-Type", "application/json")
            .bodyValue(loginRequest)
            .retrieve()
            .toEntity(String.class)
            .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            return objectMapper.readValue(response.getBody(), LoginResponse.class);
        }
        return null; 
    }

    public void logout(String accessToken) throws Exception {
        String logoutUrl = baseApiUrl.trim() + "auth/logout";
        WebClient client = webClientBuilder.baseUrl(logoutUrl).build();

        client.post()
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .toEntity(String.class)
            .block();
    }

    public DashboardResponse getDashboardData(String accessToken) throws Exception {
        String dashboardUrl = baseApiUrl.trim() + "dashboard";
        WebClient client = webClientBuilder.baseUrl(dashboardUrl).build();
    
        ResponseEntity<String> response = client.get()
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .toEntity(String.class)
            .block();
    
        System.out.println("API Response: " + response.getBody()); // Ajouter ceci pour voir la réponse brute
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            return objectMapper.readValue(response.getBody(), DashboardResponse.class);
        }
        throw new Exception("Failed to fetch dashboard data: " + (response != null ? response.getStatusCode() : "No response"));
    }
}