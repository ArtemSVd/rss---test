package com.aggregator.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * POJO - класс для работы с сущностями новостных категорий.
 */
@Entity
@Table(name = "category")
public class Category {
    /**
     * Уникальный идентификатор категории.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private long id;
    /**
     * Название категории.
     */
    @Column(name = "topic")
    private String topic;
    /**
     * Ссылка на RSS - ленту.
     */
    @Column(name = "url")
    private String url;
    /**
     * Дата последнего обновления новостей
     * по конкретной категории.
     */
    @Column(name = "last_update")
    private Date lastUpdate = new Date(0);
    /**
     * Набор новостей по данной категории.
     */
    @OneToMany(mappedBy = "category",
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<News> newsSet;
    /**
     * Набор пользователей, которые выбрали данную категорию.
     */
    @ManyToMany
    @JoinTable(name = "users_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Category(
            final String topic,
            final String url) {

        this.topic = topic;
        this.url = url;
    }

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<News> getNewsSet() {
        return newsSet;
    }

    public void setNewsSet(final Set<News> newsSet) {
        this.newsSet = newsSet;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
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


