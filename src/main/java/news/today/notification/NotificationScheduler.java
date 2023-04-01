package news.today.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import news.today.notification.git.GitService;
import news.today.notification.model.News;
import news.today.notification.search.SearchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationScheduler {

    private final SearchService searchService;
    private final GitService gitService;

    /**
     * 월-금 오전 8시 30분에 실행
     */
    @Scheduled(cron = "0 30 8 * * 1-5")
    public void notifyTodayNews() {
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm"));

        try {
            log.info(format + " START");
            List<News> news = searchService.getNews();
            String markdown = gitService.createMarkdown(news);
            gitService.createIssue(markdown);
            log.info(format + " END");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
