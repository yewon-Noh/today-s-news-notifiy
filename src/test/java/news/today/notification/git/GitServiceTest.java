package news.today.notification.git;

import news.today.notification.model.News;
import news.today.notification.search.SearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;;

@SpringBootTest
public class GitServiceTest {
    @Autowired
    private SearchService searchService;

    @Autowired
    private GitService gitService;

    @DisplayName("뉴스 데이터로 마크다운 생성")
    @Test
    void createMarkdown() {
        // given
        List<News> news = searchService.getNews();

        // when
        String markdown = gitService.createMarkdown(news);

        // then
        System.out.println(markdown);
    }

    @DisplayName("깃에 Issue 등록")
    @Test
    void createIssueTest() {
        // given
        String markdown = "";
        markdown += "## 정치\n\n";
        markdown += "[[조선일보] “늦여름, 초가을쯤엔...” 민주당 지도부서도 이재명 퇴진론](https://news.zum.com/articles/81832741)\n\n";
        markdown += "- 더불어민주당 고민정(오른쪽) 최고위원이 13일 국회에서 열린 최고위원 회의에서 발언하고 있다. 고 최고위원은 이날 라디오에 출연해 이재명(왼쪽) 대표 사퇴설에 대해 “늦여름, 초가을 정도 되면 총선을 몇 달 앞으로 남겨두고 있기 때문에 당도 총선 전략을 무엇으로 가야 할 것인지 판단할 수밖에 없을 것”이라고 했다. 가운데는 박홍근 원내대표./연합뉴스 더불어민주당 고민정 최고위원은 13일 이재명 대표 사퇴설에 대해 “늦여름, 초가을 정도 되면 총선을 몇 달 앞으로 남겨두고 있기 때문에 당도 총선 전략을 무엇으로 짜야 할 것인지 판단할 수밖에 없을 것”이라고 했다. 문재인 정부 청와대 대변인 출신인 고 최고위원은 지도부 내 유일한 비명(비이재명)계 인사로 분류된다. 고 최고위원의 발언은 비명계가 주장하는 이 대표 사퇴의 ‘데드라인’을 제시한 것으로 해석됐다. 고 최고위원은 이날 SBS 라디오에서 “옳고 그름이 아니라 판단의 문제다. 이 대표를 지키자는 의견과 이 대표로는 선거가 어렵다는";
        markdown += "\n\n";

        // when
        gitService.createIssue(markdown);

        // then
    }

    @DisplayName("뉴스 데이터를 마크다운으로 생성하여 깃에 Issue 등록")
    @Test
    void createMarkdownAndIssueTest() {
        // given
        List<News> news = searchService.getNews();

        // when
        String markdown = gitService.createMarkdown(news);
        gitService.createIssue(markdown);

        // then
    }
}
