package mg.itu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model; 
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.base-url}")
    private String baseApiUrl;

    @GetMapping("/")
    public String index() {
        return "views/auth/login"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, 
                        @RequestParam("password") String password,
                        Model model) { 
        String loginUrl = baseApiUrl.trim() + "/login";

        
        String jsonCredentials = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        try {
            WebClient client = webClientBuilder.baseUrl(loginUrl).build();
            ResponseEntity<String> response = client.post()
                .header("Content-Type", "application/json")
                .bodyValue(jsonCredentials)
                .retrieve()
                .toEntity(String.class)
                .block();

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                
                return "views/pages/dashboard";
            } else if (response != null && response.getStatusCode().value() == 401) {
                
                model.addAttribute("error", "Invalid email or password");
                return "views/auth/login";
            }

            
            model.addAttribute("error", "An unexpected error occurred");
            return "views/auth/login";

        } catch (Exception e) {
            
            model.addAttribute("error", "Invalid email or password"); 
            return "views/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        String logoutUrl = baseApiUrl.trim() + "/logout";
        WebClient client = webClientBuilder.baseUrl(logoutUrl).build();
        ResponseEntity<String> response = client.post()
            .retrieve()
            .toEntity(String.class)
            .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/api/auth/";
        }

        return "views/auth/login";
    }
}