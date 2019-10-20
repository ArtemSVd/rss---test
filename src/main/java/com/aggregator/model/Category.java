package com.aggregator.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import  javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",unique = true, nullable = false)
    private long id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "url")
    private String url;
    @Column(name = "last_update")
    private Date lastUpdate = new Date(0);
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<News> newsSet;
    @ManyToMany
    @JoinTable(name="users_category",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> users;

    public Category(String topic, String url) {
        this.topic = topic;
        this.url = url;
    }

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<News> getNewsSet() {
        return newsSet;
    }

    public void setNewsSet(Set<News> newsSet) {
        this.newsSet = newsSet;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + id +
                ", topic='" + topic + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}


