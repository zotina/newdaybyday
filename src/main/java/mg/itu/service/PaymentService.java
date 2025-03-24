package mg.itu.service;

import mg.itu.model.payment.Payment;
import mg.itu.model.payment.PaymentResponse;
import mg.itu.model.payment.PaymentData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service  
public class PaymentService {
 
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${api.base-url}")
    private String baseApiUrl;

    public List<Payment> getPayments(String accessToken, String externalId) throws Exception {
        String paymentsUrl = baseApiUrl.trim() + "payment" + (externalId != null ? "?external_id=" + externalId : "");

        WebClient client = webClientBuilder.baseUrl(paymentsUrl).build();
 
        ResponseEntity<PaymentResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(PaymentResponse.class)
                .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            PaymentResponse paymentResponse = response.getBody();
            if (paymentResponse != null) {
                Object data = paymentResponse.getData();
                if (data instanceof PaymentData) {
                    return ((PaymentData) data).getPayments();
                } else if (data instanceof Payment) {
                    return Collections.singletonList((Payment) data);
                } else if (data instanceof Map) {
                    Map<String, Object> dataMap = (Map<String, Object>) data;
                    if (dataMap.containsKey("payments")) {
                        return objectMapper.convertValue(data, PaymentData.class).getPayments();
                    } else {
                        Payment payment = objectMapper.convertValue(data, Payment.class);
                        return Collections.singletonList(payment);
                    }
                } else {
                    throw new Exception("Unexpected data format in response: " + (data != null ? data.getClass().getName() : "null"));
                }
            } else {
                throw new Exception("API returned unsuccessful response: " + (paymentResponse != null ? paymentResponse.getMessage() : "No response body"));
            }
        } else {
            throw new Exception("Failed to fetch payments: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }

    public void updatePayment(String accessToken, String externalId, Double amount) throws Exception {
        String updateUrl = baseApiUrl.trim() + "payment/update-amount";

        WebClient client = webClientBuilder.baseUrl(updateUrl).build();

        ResponseEntity<PaymentResponse> response = client.post()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("paymentExternalId", externalId, "montant", amount))
                .retrieve()
                .toEntity(PaymentResponse.class)
                .block();

        if (response == null || !response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to update payment: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }

    public void deletePayment(String accessToken, String externalId) throws Exception {
        String deleteUrl = baseApiUrl.trim() + "payment/delete";

        WebClient client = webClientBuilder.baseUrl(deleteUrl).build();

        ResponseEntity<PaymentResponse> response = client.post()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("external_id", externalId))
                .retrieve()
                .toEntity(PaymentResponse.class)
                .block();

        if (response == null || !response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to delete payment: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }
}