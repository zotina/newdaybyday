package mg.itu.service;

import mg.itu.model.setting.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SettingService {

    private static final Logger logger = LoggerFactory.getLogger(SettingService.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.base-url}")
    private String baseApiUrl;

    public void updateDiscount(String accessToken, Double remise) throws Exception {
        String url = baseApiUrl.trim() + "settings/update-discount";
        logger.info("Updating discount at URL: " + url);

        WebClient client = webClientBuilder.baseUrl(url).build();

        Setting setting = new Setting(remise);

        String response = client.post()
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(setting))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        logger.info("Response from update discount: " + response);
    }
}