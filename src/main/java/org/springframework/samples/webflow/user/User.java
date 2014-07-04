package org.springframework.samples.webflow.user;

import org.hibernate.annotations.ForeignKey;
import org.springframework.samples.webflow.news.Article;

import javax.persistence.*;

/**
 * Created by orcwarrior on 2014-07-03.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @JoinColumn(name = "ROLE", referencedColumnName = "ID")
    @ManyToOne
    @ForeignKey(name="FK_ROLE_ID")
    private UserRole role;
}
