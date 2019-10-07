package com.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
  //  @Column(name = "pub_date")
   // private String pubDate;
    @Column(name = "date")
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;

    @Transient
    private boolean ready;

    public News() { }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

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
        ready = title != null && date!= null && url != null ;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
       ready = title != null && date!= null && url != null;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        ready = title != null && date!= null && url != null ;
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
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        Date date = format.parse(pubDate);
        this.date = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm");

       // this.pubDate = sdf.format(date);

        ready = title != null && date!= null && url != null ;
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
}
