package news.today.notification.search;

import news.today.notification.model.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search", method = { RequestMethod.GET })
    public List<News> search(String category) {
        return searchService.getNewsByTheme(category);
    }
}
