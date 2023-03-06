package news.today.notification.search;

import news.today.notification.model.News;

import java.util.List;

public interface SearchService {

    public List<News> getNewsByTheme(String category);
}
