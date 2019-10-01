package gui.model;

import org.hibernate.annotations.GenericGenerator;

import  javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rss_link")
public class RssLink {

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(unique = true, nullable = false)
    private int id = 1;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

    public RssLink(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public RssLink() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RssLink{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RssLink rssLink = (RssLink) o;
        return id == rssLink.id &&
                Objects.equals(name, rssLink.name) &&
                Objects.equals(url, rssLink.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}
