package org.springframework.samples.webflow.news.comment;

import org.apache.derby.client.am.DateTime;
import org.hibernate.annotations.ForeignKey;
import org.springframework.samples.webflow.news.Article;
import org.springframework.samples.webflow.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by orcwarrior on 2014-07-03.
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "POSTED")
    private Timestamp posted;

    @Basic
    @Column(name = "POINTS")
    private int points;

    @JoinColumn(name = "AUTHOR", referencedColumnName = "ID")
    @ManyToOne
    @ForeignKey(name="FK_AUTHOR")
    private User author;

}
