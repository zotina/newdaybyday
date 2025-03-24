package mg.itu.service;

import mg.itu.model.project.Project;
import mg.itu.model.project.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.base-url}")
    private String baseApiUrl;

    
    public List<Project> getProjects(String accessToken) throws Exception {
        String projectsUrl = baseApiUrl.trim() + "project"; 

        WebClient client = webClientBuilder.baseUrl(projectsUrl).build();

        ResponseEntity<ProjectResponse> response = client.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .toEntity(ProjectResponse.class)
                .block();

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            ProjectResponse projectResponse = response.getBody();
            if (projectResponse != null && projectResponse.isSuccess()) {
                return projectResponse.getData();
            } else {
                throw new Exception("API returned unsuccessful response: " + projectResponse.getMessage());
            }
        } else {
            throw new Exception("Failed to fetch projects: " + (response != null ? response.getStatusCode() : "No response"));
        }
    }
}