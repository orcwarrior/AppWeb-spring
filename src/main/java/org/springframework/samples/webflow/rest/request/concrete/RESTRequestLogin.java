package org.springframework.samples.webflow.rest.request.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.samples.webflow.rest.request.RESTRequestWithoutSession;
import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.rest.response.RESTResponse;
import org.springframework.samples.webflow.rest.response.RESTResponseFactory;
import org.springframework.samples.webflow.user.User;
import org.springframework.samples.webflow.user.UserDao;
import org.springframework.samples.webflow.user.session.UserSession;
import org.springframework.samples.webflow.user.session.UserSessionService;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public class RESTRequestLogin extends RESTRequestWithoutSession {

    @Autowired
    UserDao userDao;

    @Autowired
    UserSessionService userSessionService;

    public RESTRequestLogin(RESTRequestHeaders headers, Object content) {
        super(headers, content);
    }

    @Override
    public RESTResponse doAction() {
        RESTResponse response;
        User user;
        try {
            user = (User) this.getContent();
        } catch (Exception e) {
            return RESTResponseFactory.createRESTResponse("Passed object don't match with User class, exception: " + e.getMessage());
        }
        User userDB = userDao.getUserByName(user.getUsername());
        if (user.equals(userDB)) {
            UserSession session = userSessionService.generateUserSession(user);
            return RESTResponseFactory.createRESTResponse(session, session);
        }
        return RESTResponseFactory.createRESTResponse("No user found with name: " + user.getUsername());

    }
}
