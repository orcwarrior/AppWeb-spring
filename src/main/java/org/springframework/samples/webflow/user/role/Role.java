package org.springframework.samples.webflow.user.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by orcwarrior on 2014-07-03.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    // private Set<User> userRoles;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // public Set<User> getUserRoles() {
    //     return userRoles;
    // }
//
    // public void setUserRoles(Set<User> userRoles) {
    //     this.userRoles = userRoles;
    // }

}
