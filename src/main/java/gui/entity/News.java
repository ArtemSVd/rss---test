package gui.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name = "news_id", nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId= 1;
    @Column(name = "title")
    private String title;
    @Column(name = "news_url")
    private String url;
    @Column(name = "pub_date")
    private String pubDate;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
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
        ready = title != null && pubDate!= null && url != null ;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
       ready = title != null && pubDate!= null && url != null;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;

    }

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        category.getNews().add(this);
        this.category = category;
        ready = title != null && pubDate!= null && url != null ;
    }

    public void setPubDateFromXml(String pubDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        Date date = format.parse(pubDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm");

        this.pubDate = sdf.format(date);

        ready = title != null && pubDate!= null && url != null ;
    }


}
