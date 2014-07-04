package org.springframework.samples.webflow.news;

import org.hibernate.annotations.ForeignKey;
import org.springframework.samples.webflow.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

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


    @JoinColumn(name = "AUTHOR", referencedColumnName = "ID")
    @ManyToOne
    @ForeignKey(name="FK_AUTHOR")
    private User author;

    @Column(name="CREATED")
    private Timestamp created;

    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID")
    @ManyToOne
    @ForeignKey(name="FK_MODIFIEDBY")
    private User modifiedBy;


    @Column(name="MODIFIED_DATE")
    private Timestamp modifiedDate;


    public Article() {
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
