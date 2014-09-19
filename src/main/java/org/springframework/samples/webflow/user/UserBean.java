package org.springframework.samples.webflow.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.news.ArticleDao;
import org.springframework.samples.webflow.news.ArticleService;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by orcwarrior on 2014-07-09.
 */
@Component
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    @Inject
    public UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
