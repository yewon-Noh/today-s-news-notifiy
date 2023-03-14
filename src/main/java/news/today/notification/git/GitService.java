package news.today.notification.git;

import news.today.notification.model.News;

import java.util.List;

public interface GitService {
    public String createMarkdown(List<News> news);
    public void createIssue(String markdown);
}
