package mg.itu.service;

import mg.itu.model.invoice.Invoice;
import mg.itu.model.invoice.InvoiceResponse;
import mg.itu.model.invoice.InvoiceData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper; // Ajout de ObjectMapper pour conversion

    @Value("${api.base-url}")
    private String baseApiUrl;

    public List<Invoice> getInvoices(String accessToken, String externalId) throws Exception {
        String invoicesUrl = baseApiUrl.trim() + "invoice" + (externalId != null ? "?external_id=" + externalId : "");

        WebClient client = webClientBuilder.baseUrl(invoicesUrl).build();

        ResponseEntity<InvoiceResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(InvoiceResponse.class)
                .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            InvoiceResponse invoiceResponse = response.getBody();
            if (invoiceResponse != null) {
                Object data = invoiceResponse.getData();
                if (data instanceof InvoiceData) {
                    return ((InvoiceData) data).getInvoices(); // Liste paginée
                } else if (data instanceof Invoice) {
                    return Collections.singletonList((Invoice) data); // Facture unique (peu probable ici)
                } else if (data instanceof Map) {
                    // Cas où data est un LinkedHashMap (facture unique ou autre objet JSON)
                    Map<String, Object> dataMap = (Map<String, Object>) data;
                    if (dataMap.containsKey("invoices")) {
                        // Liste paginée reçue comme Map
                        return objectMapper.convertValue(data, InvoiceData.class).getInvoices();
                    } else {
                        // Facture unique reçue comme Map
                        Invoice invoice = objectMapper.convertValue(data, Invoice.class);
                        return Collections.singletonList(invoice);
                    }
                } else {
                    throw new Exception("Unexpected data format in response: " + (data != null ? data.getClass().getName() : "null"));
                }
            } else {
                throw new Exception("API returned unsuccessful response: " + (invoiceResponse != null ? invoiceResponse.getMessage() : "No response body"));
            }
        } else {
            throw new Exception("Failed to fetch invoices: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }

    public Double getAmountDue(String accessToken, String externalId) throws Exception {
        if (externalId == null) {
            throw new Exception("External ID is required for amount due");
        }

        String amountDueUrl = baseApiUrl.trim() + "invoice/amount-due?external_id=" + externalId;

        WebClient client = webClientBuilder.baseUrl(amountDueUrl).build();

        ResponseEntity<InvoiceResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(InvoiceResponse.class)
                .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            InvoiceResponse invoiceResponse = response.getBody();
            if (invoiceResponse != null && "success".equals(invoiceResponse.getType())) {
                return (Double) invoiceResponse.getData();
            } else {
                throw new Exception("API returned unsuccessful response: " + invoiceResponse.getMessage());
            }
        } else {
            throw new Exception("Failed to fetch amount due: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }
}