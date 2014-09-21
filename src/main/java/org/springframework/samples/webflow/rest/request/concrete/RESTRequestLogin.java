package org.springframework.samples.webflow.rest.request.concrete;

import com.google.gson.internal.LinkedTreeMap;
import org.springframework.samples.webflow.rest.request.RESTRequestWithoutSession;
import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.rest.response.RESTResponse;
import org.springframework.samples.webflow.rest.response.RESTResponseFactory;
import org.springframework.samples.webflow.user.User;
import org.springframework.samples.webflow.user.session.UserSession;
import org.springframework.stereotype.Controller;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Controller
public class RESTRequestLogin extends RESTRequestWithoutSession {


    public RESTRequestLogin(){
        super(new RESTRequestHeaders(), new Object());
    }

    public RESTRequestLogin(RESTRequestHeaders headers, Object content) {
        super(headers, content);
    }

    @Override
    public RESTResponse doAction() {
        RESTResponse response;
        RequestUser requestUser;
        LinkedTreeMap content = this.getContentTree();
        try {
            requestUser = new RequestUser(content.get("username").toString(), content.get("password").toString());
        } catch (Exception e) {
            if (e.getMessage() == null)
            return RESTResponseFactory.createRESTResponse("Passed object don't match with User class, exception: " + e.toString());
            else
                return RESTResponseFactory.createRESTResponse("Passed object don't match with User class, exception: "
                        + e.toString() + ", exception message: " + e.getMessage());

        }
        User userDB = staticUserService.getUserByUsername(requestUser.getUsername());
        if (userDB != null && userDB.getPassword().equals(requestUser.getPassword()) ) {
            UserSession session = staticUserSessionService.generateUserSession(userDB);
            return RESTResponseFactory.createRESTResponse(session, session);
        }
        return RESTResponseFactory.createRESTResponse("Invalid username or password: " + requestUser.getUsername());

    }
    class RequestUser {
        String username;
        String password;

        RequestUser(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
