package org.springframework.samples.webflow.user;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by orcwarrior on 2014-07-04.
 */
@Service
public class UserService {


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
}
