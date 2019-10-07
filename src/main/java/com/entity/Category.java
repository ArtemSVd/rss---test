package com.entity;

import  javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id",unique = true, nullable = false)
    private int categoryId;
    @Column(name = "topic")
    private String topic;
    @Column(name = "url")
    private String url;

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

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", topic='" + topic + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}


