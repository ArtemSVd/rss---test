package com.model;

import com.TimeUtil;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name = "news_id", nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;
    @Column(name = "title")
    private String title;
    @Column(name = "news_url")
    private String url;
    @Column(name = "date")
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;

    public News() { }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getPubDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        return sdf.format(date);
    }
    public void setDateFromXml(String pubDate) throws ParseException {
        Date date = TimeUtil.parseDate(pubDate);
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(url, news.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}
