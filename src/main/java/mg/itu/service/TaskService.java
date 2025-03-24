package mg.itu.service;

import mg.itu.model.task.Task;
import mg.itu.model.task.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import org.springframework.web.util.UriComponentsBuilder;
 
@Service 
public class TaskService {
 
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.base-url}")
    private String baseApiUrl;



public List<Task> getTasks(String accessToken, int page, int perPage) throws Exception {
    String tasksUrl = UriComponentsBuilder.fromHttpUrl(baseApiUrl.trim() + "task")
            .queryParam("page", page)
            .queryParam("per_page", perPage)
            .toUriString();
    
    WebClient client = webClientBuilder.baseUrl(tasksUrl).build();

    ResponseEntity<TaskResponse> response = client.get()
            .header("Authorization", "Bearer " + accessToken)
            .retrieve()
            .toEntity(TaskResponse.class)
            .block();
 
    if (response != null && response.getStatusCode().is2xxSuccessful()) {
        TaskResponse taskResponse = response.getBody();
        if (taskResponse != null && taskResponse.isSuccess()) {
            return taskResponse.getData();
        } else {
            throw new Exception("API returned unsuccessful response: " + taskResponse.getMessage());
        }
    } else {
        throw new Exception("Failed to fetch tasks: " + (response != null ? response.getStatusCode() : "No response"));
    }
}

}