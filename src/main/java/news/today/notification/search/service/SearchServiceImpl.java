package news.today.notification.search.service;

import news.today.notification.search.Driver;
import news.today.notification.search.SearchService;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private final Driver driver;

    public SearchServiceImpl(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getNewsByTheme(String category) {
        driver.searchNews(category);
        return null;
    }
}
