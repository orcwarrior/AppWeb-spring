package org.springframework.samples.webflow.article;

import org.hibernate.annotations.ForeignKey;
import org.springframework.samples.webflow.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Dariusz on 2014-05-12.
 */
@Entity
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @JoinColumn(name = "AUTHOR", referencedColumnName = "USERNAME")
    @ManyToOne
    @ForeignKey(name = "FK_AUTHOR")
    private User author;

    @Column(name = "CREATED")
    private Date created;

    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "USERNAME")
    @ManyToOne
    @ForeignKey(name = "FK_MODIFIEDBY")
    private User modifiedBy;


    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;


    public Article(int id, String title, String content) {
        this(title, content);
        this.id = id;
    }

    public Article(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public Article() {
        this.created = new java.util.Date();
    }

    public Article(String title, String content, String imageUrl, User author) {
        this(title, content);
        this.author = author;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void modifyArticle(String title, String content, String imageUrl, User modificator) {
        if (title != null)
            setTitle(title);
        if (content != null) ;
        setContent(content);
        if (imageUrl != null)
            setImageUrl(imageUrl);
        if (title != null || content != null || imageUrl != null) {
            this.modifiedBy = modificator;
            this.modifiedDate = new java.util.Date();
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (content != null ? !content.equals(article.content) : article.content != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;

        return true;
    }
}
