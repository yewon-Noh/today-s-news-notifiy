package news.today.notification.git;

import news.today.notification.model.Issue;
import news.today.notification.model.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class GitServiceImpl implements GitService {

    private String apiUrl = "https://api.github.com/repos/{OWNER}/{REPO}/issues";

    @Value("${git.owner}")
    private String owner;

    @Value("${git.repo}")
    private String repo;

    @Value("${git.token}")
    private String token;

    private RestTemplate restTemplate;

    @Override
    public String createMarkdown(List<News> news) {
        StringBuffer sb = new StringBuffer();

        HashMap<String, String> categoryMap = new HashMap<>();
        for (News news1 : news) {
            String category = news1.getCategory();
            if (!categoryMap.containsKey(category)) {
                sb.append("## ").append(category).append("\n\n");
                categoryMap.put(category, "ok");
            }

            sb.append("[[").append(news1.getCompany()).append("] ").append(news1.getTitle()).append("](").append(news1.getUrl()).append(")\n\n");
            sb.append("- ").append(news1.getContent());
            sb.append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public void createIssue(String markdown) {
        String url = apiUrl.replace("{OWNER}", owner).replace("{REPO}", repo);

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setBearerAuth(token);

        Issue issue = new Issue(markdown);
        HttpEntity<Issue> request = new HttpEntity<>(issue, headers);

        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Failed to create issue. Status code: " + response.getStatusCode());
        }
    }
}
