package mg.itu.service;

import mg.itu.model.chart.ChartResponse;
import mg.itu.model.chart.ClientOverview;
import mg.itu.model.chart.InvoiceStatus;
import mg.itu.model.chart.PaymentSummary;
import mg.itu.model.chart.TaskStatus;
import mg.itu.model.payment.PaymentMonth;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class ChartService {

    private static final Logger logger = LoggerFactory.getLogger(ChartService.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${api.base-url}")
    private String baseApiUrl;

    public ClientOverview getClientOverview(String accessToken, String externalId) throws Exception {
        String url = baseApiUrl.trim() + "chart1" + (externalId != null ? "?external_id=" + externalId : "");
        return fetchChartData(url, accessToken, ClientOverview.class);
    }

    public InvoiceStatus getInvoiceStatus(String accessToken, String externalId) throws Exception {
        String url = baseApiUrl.trim() + "chart2" + (externalId != null ? "?external_id=" + externalId : "");
        return fetchChartData(url, accessToken, InvoiceStatus.class);
    }

    public PaymentSummary getPaymentSummary(String accessToken, String month) throws Exception {
        String url = baseApiUrl.trim() + "chart3" + (month != null ? "?month=" + month : "");
        return fetchChartData(url, accessToken, PaymentSummary.class);
    }

    private <T> T fetchChartData(String url, String accessToken, Class<T> responseType) throws Exception {
        logger.info("Fetching chart data from URL: " + url);

        WebClient client = webClientBuilder.baseUrl(url).build();

        ResponseEntity<ChartResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(ChartResponse.class)
                .block();

        if (response == null || !response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to fetch chart data: HTTP Status=" + 
                (response != null ? response.getStatusCode() : "No response"));
        }

        ChartResponse chartResponse = response.getBody();
        logger.info("Response body: " + chartResponse);

        if (chartResponse != null ) {
            Object data = chartResponse.getData();
            if (data == null) {
                throw new Exception("No data found in response");
            }

            
            if (data instanceof List) {
                List<?> dataList = (List<?>) data;
                if (dataList.isEmpty()) {
                    throw new Exception("Data array is empty");
                }
                return objectMapper.convertValue(dataList.get(0), responseType); 
            } else {
                return objectMapper.convertValue(data, responseType); 
            }
        } else {
            throw new Exception("API returned unsuccessful response: " + 
                (chartResponse != null ? chartResponse.getMessage() : "No response body"));
        }
    }
    public List<PaymentMonth> getPaymentSummaryByMonth(String accessToken, String year) throws Exception {
        String url = baseApiUrl.trim() + "chart/payment-summary-by-month" + (year != null ? "?year=" + year : "");
        logger.info("Fetching payment summary by month from URL: " + url);
    
        WebClient client = webClientBuilder.baseUrl(url).build();
    
        ResponseEntity<ChartResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(ChartResponse.class)
                .block();
    
        if (response == null || !response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to fetch payment summary by month: HTTP Status=" + 
                (response != null ? response.getStatusCode() : "No response"));
        }
    
        ChartResponse chartResponse = response.getBody();
        logger.info("Response body: " + chartResponse);
    
        if (chartResponse != null) { // Changé de getType() à getStatus()
            Object data = chartResponse.getData();
            if (data == null) {
                throw new Exception("No data found in response");
            }
            return objectMapper.convertValue(data, objectMapper.getTypeFactory().constructCollectionType(List.class, PaymentMonth.class));
        } else {
            throw new Exception("API returned unsuccessful response: " + 
                (chartResponse != null ? "No response body" : "No response body"));
        }
    }

    public List<TaskStatus> getTaskStatusSummary(String accessToken) throws Exception {
        String url = baseApiUrl.trim() + "chart/task-status-summary";
        logger.info("Fetching task status summary from URL: " + url);

        WebClient client = webClientBuilder.baseUrl(url).build();

        ResponseEntity<ChartResponse> response = client.get()
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON) // Indique que JSON est attendu
            .retrieve()
            .toEntity(ChartResponse.class)
            .block();
            
            
        if (response == null || !response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to fetch task status summary: HTTP Status=" + 
            (response != null ? response.getStatusCode() : "No response"));
        }
            ChartResponse chartResponse = response.getBody();
 
        if (chartResponse != null && "success".equals(chartResponse.getStatus())) {
            Object data = chartResponse.getData();
            if (data == null) {
                throw new Exception("No data found in response");
            }
            return objectMapper.convertValue(data, objectMapper.getTypeFactory().constructCollectionType(List.class, TaskStatus.class));
        } else {
            throw new Exception("API returned unsuccessful response: " + 
                (chartResponse != null ? chartResponse.getMessage() : "No response body"));
        }
    }
     
    public List<InvoiceStatus> getInvoiceStatusSummary(String accessToken) throws Exception {
        String url = baseApiUrl.trim() + "chart/invoice-status-summary";
        logger.info("Fetching invoice status summary from URL: " + url);

        WebClient client = webClientBuilder.baseUrl(url).build();

        ResponseEntity<ChartResponse> response = client.get()
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(ChartResponse.class)
            .block();

        ChartResponse chartResponse = response.getBody();
        logger.info("Response body: " + chartResponse);

        return objectMapper.convertValue(chartResponse.getData(), 
            objectMapper.getTypeFactory().constructCollectionType(List.class, InvoiceStatus.class));
    }
}