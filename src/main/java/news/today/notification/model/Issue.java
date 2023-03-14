package news.today.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class Issue {
    private String title;
    private String body;

    public Issue(String body) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 뉴스");
        this.title = now.format(formatter);
        this.body = body;
    }
}
