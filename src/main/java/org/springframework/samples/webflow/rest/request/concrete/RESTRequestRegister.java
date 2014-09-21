package org.springframework.samples.webflow.rest.request.concrete;

import com.google.gson.internal.LinkedTreeMap;
import org.springframework.samples.webflow.rest.request.RESTRequestWithoutSession;
import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.rest.response.RESTResponse;
import org.springframework.samples.webflow.rest.response.RESTResponseFactory;
import org.springframework.samples.webflow.user.User;
import org.springframework.samples.webflow.user.session.UserSession;

/**
 * Created by orcwarrior on 2014-09-21.
 */
public class RESTRequestRegister extends RESTRequestWithoutSession {
    public RESTRequestRegister(RESTRequestHeaders headers, Object content) {
        super(headers, content);
    }

    @Override
    public RESTResponse doAction() {
        String username, passwordHash, email;
        RESTResponse response;
        LinkedTreeMap content = this.getContentTree();
        try {
            username = content.get("username").toString();
            passwordHash = content.get("password").toString();
            email = content.get("email").toString();
        } catch (Exception e) {
            if (e.getMessage() == null)
                return RESTResponseFactory.createRESTResponse("Passed object don't match with User class, exception: " + e.toString());
            else
                return RESTResponseFactory.createRESTResponse("Passed object don't match with User class, exception: "
                        + e.toString() + ", exception message: " + e.getMessage());

        }
        // Check if there is already user with this username:
        User userDB = staticUserService.getUserByUsername(username);
        if (userDB != null) return RESTResponseFactory.createRESTResponse("User with this name already exist!");
        // Create user and get his session:
        User newUser = staticUserService.createNewUser(username, passwordHash, email);
        UserSession session = staticUserSessionService.getUserSessionByUser(newUser);
        return RESTResponseFactory.createRESTResponse(session, session);

    }
}
