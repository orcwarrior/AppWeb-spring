package org.springframework.samples.webflow.user;

import com.sun.istack.NotNull;
import org.springframework.samples.webflow.user.role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by orcwarrior on 2014-07-03.
 */
@Entity
@Table(name = "users")
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
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

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.enabled = true;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null && obj instanceof User)
            return this.getUsername() == ((User) obj).getUsername();
        return false;
    }

    public String getUsername() {
        return username;
    }
    public void addUserRole(Role r) {
        if(this.roles == null)
            this.roles = new HashSet<Role>();
        this.roles.add(r);
    }
}
