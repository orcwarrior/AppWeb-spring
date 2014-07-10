package org.springframework.samples.webflow.user;

import com.sun.istack.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.springframework.samples.webflow.user.role.Role;

import javax.persistence.*;
import java.util.Set;

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

    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Basic
    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
    private Set<Role> roles;

    @Basic
    @NotNull
    @Column(name = "ENABLED")
    private boolean enabled; // spring seciurity: is user enabled

    @Basic
    @Column(name = "EMAIL")
    private String email;

    public User() {
    }
}
