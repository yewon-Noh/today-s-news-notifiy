package news.today.notification.search.service;

import news.today.notification.model.Category;
import news.today.notification.model.News;
import news.today.notification.search.Driver;
import news.today.notification.search.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<News> getNews() {
        List<News> news = new ArrayList<>();
        for (Category ct: Category.values()) {
            news.addAll(driver.searchNews(ct.getEng()));
        }
        return news;
    }
}
