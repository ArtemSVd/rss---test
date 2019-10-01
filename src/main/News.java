package main;

import java.util.Objects;

public class News {
    String title;
    String link;
    String pubDate;
    boolean newsReady =false;

    public News() {


    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';

    }

    public void setTitle(String title) {
        this.title = title;
        newsReady = !(title.equals(null) && link.equals(null) && pubDate.equals(null));
    }

    public void setLink(String link) {
        this.link = link;
        newsReady = !(title.equals(null) && link.equals(null) && pubDate.equals(null));
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
        newsReady = !(title.equals(null) && link.equals(null) && pubDate.equals(null));
    }

    public boolean isNewsReady() {
        return newsReady;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return newsReady == news.newsReady &&
                Objects.equals(title, news.title) &&
                Objects.equals(link, news.link) &&
                Objects.equals(pubDate, news.pubDate);
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, pubDate, newsReady);
    }
}
