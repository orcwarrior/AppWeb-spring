package org.springframework.samples.webflow.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.user.role.Role;
import org.springframework.samples.webflow.user.role.RoleService;
import org.springframework.samples.webflow.user.session.UserSessionService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by orcwarrior on 2014-07-04.
 */
@Service
public class UserService implements Serializable {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleService roleService;
    @Autowired
    UserSessionService userSessionService;

    public Boolean isLoggedIn() {
        return isAuthenticated() && !isAnymouslyAuthenticated();
    }
    public Boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof Authentication) {
            return true;
        }
        return false;
    }
    public Boolean isAnymouslyAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return true;
        }
        return false;

    }
    public Boolean hasAuthority(String roleName)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(roleName);
    }
    public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    public User getUserByUsername(String username) {
        return userDao.getUserByName(username);
    }
    public boolean userWithNameExist(String username) {
        return getUserByUsername(username) != null;
    }
    public User createNewUser(String username, String passwordHash, String email) {
        User newUser = new User(username, passwordHash, email);
        Role baseUserRole = roleService.getDefaultUsersRole();
        newUser.addUserRole(baseUserRole);
        userDao.saveUser(newUser);
        userSessionService.generateUserSession(newUser);
        return newUser;
    }
}
