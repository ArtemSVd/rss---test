package gui.entity;

import  javax.persistence.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",unique = true, nullable = false)
    private int categoryId = 1;
    @Column(name = "topic")
    private String topic;
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private Set<News> news = new HashSet<>();

    public Category(String topic, String url) {
        this.topic = topic;
        this.url = url;
    }

    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}


