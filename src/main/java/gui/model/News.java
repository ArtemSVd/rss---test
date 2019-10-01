package gui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class News implements Comparable {
    private String title;
    private String link;
    private String pubDate;
    private boolean newsReady =false;
    private Date date;
    public News() { }

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
        newsReady = title!=null && link!=null && pubDate!=null;
    }

    public void setLink(String link) {
        this.link = link;
        newsReady = title!=null && link!=null && pubDate!=null;
    }

    public void setPubDate(String pubDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        Date date = format.parse(pubDate);
        this.date = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.");

        this.pubDate = sdf.format(date);
        newsReady = title!=null && link!=null && pubDate!=null;
    }

    private Date getDate() {
            return date;
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


    @Override
    public int compareTo(Object o) {
        News news = (News) o;
        return (int)(news.date.getTime() - getDate().getTime());
    }
}
