package news.today.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum Category {
    POLICY(1, "policy", "정치"),
    SOCIETY( 2, "society","사회"),
    ECONOMY(3, "economy", "경제"),
    INTERNATIONAL( 4, "international","국제"),
    IT(5, "it", "IT/과학");

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Category::getEng, Category::name))
    );

    @Getter
    private final int num;
    @Getter
    private final String eng;
    @Getter
    private final String kor;

    public static Category of(final String eng) {
        return Category.valueOf(CODE_MAP.get(eng));
    }
}
