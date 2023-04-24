# 오늘의 뉴스를 알려드립니다.

_🛎️ 현재 서버가 배포되어있지 않습니다. (2023-04-25)_

## 소개

- 구독을 누르면 매일 아침 카테고리별 뉴스를 메일로 전달해드립니다.
- 뉴스는 [Zum](https://news.zum.com/)에서 수집합니다.

## 구독 방법

- [yewon-Noh/news-subscribe](https://github.com/yewon-Noh/news-subscribe)에서 UnWatch를 통해 구독할 수 있습니다.

![image](https://user-images.githubusercontent.com/80824750/233573275-1729ac9e-6bc9-4c17-afda-d4674b10b941.png)

## 뉴스 예시

- 제목 : 2000년 00월 00일 뉴스
- 내용
  - 카테고리 명
      - 뉴스 제목(링크)
      - 간략한 내용
    
![image](https://user-images.githubusercontent.com/80824750/233574455-e226e845-4dff-4b0a-80f2-a09df72d276b.png)

---

## 개발 환경

- Java 17
- Spring Boot 3.0.4
- Selenium
- Chrome version 111.0.5563.64
  - [Driver 설치](https://chromedriver.storage.googleapis.com/index.html?path=111.0.5563.64/)
  > Mac 환경에서 위치 변경 방법
  > 
  > Terminal `mv chromedriver /usr/local/bin`
  > 
  > 권한 오류 시 `sudo chown -R $USER /usr/local/bin`

## 개발 중 발생한 이슈

- [java.io.IOException: Invalid Status code=403 text=Forbidden](https://github.com/yewon-Noh/today-s-news-notifiy/issues/9)
- [The import javax.annotation.PostConstruct cannot be resolved](https://github.com/yewon-Noh/today-s-news-notifiy/issues/30)

## 새롭게 공부한 내용

- [스프링 배치와 스케쥴러](https://github.com/yewon-Noh/today-s-news-notifiy/issues/31)
