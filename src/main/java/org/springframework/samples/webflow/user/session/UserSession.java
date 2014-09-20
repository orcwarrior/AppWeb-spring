package org.springframework.samples.webflow.user.session;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.samples.webflow.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Entity
@Table(name = "user_sessions")
public class UserSession {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    String uuid;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id", referencedColumnName = "ID")
    @ForeignKey(name = "fk_user_id", inverseName = "id")
    private User user;

    @Column(name = "created")
    private Date created;

    public UserSession(User user) {
        this.user = user;

        this.created = new java.util.Date();
    }

    public UserSession() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
