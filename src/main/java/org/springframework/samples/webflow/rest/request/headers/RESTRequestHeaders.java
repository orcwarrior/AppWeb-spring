package org.springframework.samples.webflow.rest.request.headers;

import org.springframework.samples.webflow.user.session.UserSession;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public class RESTRequestHeaders {
    RESTOperation operation;
    UserSession session;

    public UserSession getSession() {
        return session;
    }

    public void setSession(UserSession session) {
        this.session = session;
    }

    public RESTOperation getOperation() {
        return operation;
    }

    public void setOperation(RESTOperation operation) {
        this.operation = operation;
    }

}
