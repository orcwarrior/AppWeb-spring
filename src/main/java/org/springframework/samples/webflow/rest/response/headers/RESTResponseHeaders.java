package org.springframework.samples.webflow.rest.response.headers;

import org.springframework.samples.webflow.user.session.UserSession;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public class RESTResponseHeaders {
    RESTResponseStatus status;
    UserSession session;

    public RESTResponseHeaders(RESTResponseStatus status, UserSession session) {
        this.status = status;
        this.session = session;
    }
}
