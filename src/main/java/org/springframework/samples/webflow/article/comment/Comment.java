package org.springframework.samples.webflow.article.comment;

import org.hibernate.annotations.ForeignKey;
import org.springframework.samples.webflow.article.Article;
import org.springframework.samples.webflow.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by orcwarrior on 2014-07-03.
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "PARENT_ID")
    private int parentId;

    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Article articleId;


    @Basic
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "POSTED")
    private Date posted;

    @Basic
    @Column(name = "POINTS")
    private int points;

    @JoinColumn(name = "AUTHOR", referencedColumnName = "USERNAME")
    @ManyToOne
    @ForeignKey(name="FK_AUTHOR")
    private User author;

    public Comment(int parentId, Article articleId, String content, User author) {
        this.parentId = parentId;
        this.articleId = articleId;
        this.content = content;
        this.author = author;
        this.posted = new java.util.Date();
        this.points = 0;
    }
}
