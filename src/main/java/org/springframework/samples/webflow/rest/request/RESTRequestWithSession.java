package org.springframework.samples.webflow.rest.request;

import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.user.User;
import org.springframework.samples.webflow.user.session.UserSession;
import org.springframework.samples.webflow.user.session.UserSessionService;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public abstract class RESTRequestWithSession extends RESTRequestGeneric {
    @Autowired
    UserSessionService userSessionService;

    protected RESTRequestWithSession(RESTRequestHeaders headers, Object content) {
        super(headers, content);
    }

    @Override
    public boolean validateSession() {
        UserSession session = this.headers.getSession();
        return userSessionService.isValidSessionUUID(session.getUuid());
    }
}
