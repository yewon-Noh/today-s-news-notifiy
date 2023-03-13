package news.today.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class News {
    private String category;
    private String company;
    private String title;
    private String url;
    private String content;
}
