package org.springframework.samples.webflow.user;

import javax.persistence.*;

/**
 * Created by orcwarrior on 2014-07-03.
 */
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME")
    private String name;

}
