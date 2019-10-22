package com.aggregator.model;

import com.aggregator.util.TimeUtil;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * POJO - класс для реализации сущности новостей.
 */
@Entity
@Table(name = "news")
public class News {
    /**
     * Уникальный идентификатор.
     */
    @Id
    @Column(name = "news_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Краткая аннотация новости.
     */
    @Column(name = "title")
    private String title;
    /**
     * Ссылка на сторонний ресурс, содержащий новость.
     */
    @Column(name = "news_url")
    private String url;
    /**
     * Дата публикации .
     */
    @Column(name = "date")
    private Date date;
    /**
     * Ссылка на изображение, связанное с новостью.
     */
    @Column(name = "image")
    private String imageUrl;
    /**
     * Категория к которой относится новость.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    public News() { }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean hasImage() {
        return imageUrl != null;
    }
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getPubDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        return sdf.format(date);
    }

    public void setDateFromXml(final String pubDate)
            throws ParseException {
        this.date = TimeUtil.parseDate(pubDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        News news = (News) o;
        return Objects.equals(title, news.title)
                &&
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
