package news.today.notification.search.service;

import news.today.notification.model.News;
import news.today.notification.search.Driver;
import news.today.notification.search.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final Driver driver;

    public SearchServiceImpl(Driver driver) {
        this.driver = driver;
    }

    @Override
    public List<News> getNewsByTheme(String category) {
        return driver.searchNews(category);
    }
}
