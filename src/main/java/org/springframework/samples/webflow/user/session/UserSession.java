package org.springframework.samples.webflow.user.session;

import org.springframework.samples.webflow.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Entity
@Table(name="user_sessions")
public class UserSession {
    @Id
    @Column(name = "UUID")
    UUID uuid;

    @NotNull
    @Column(name = "USER")
    User user;

    public UserSession(User user) {
        this.user = user;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

}
