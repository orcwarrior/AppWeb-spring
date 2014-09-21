package org.springframework.samples.webflow.rest.request;

import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.user.UserService;
import org.springframework.samples.webflow.user.session.UserSessionService;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public abstract class RESTRequestGeneric implements RESTRequest {

    /* DK: I Cannot find other way to set static autowired prop */
    protected static UserService staticUserService;
    @Autowired
    public void setStaticUserDao(UserService service) {
        this.staticUserService = service;
    }

    protected static UserSessionService staticUserSessionService;
    @Autowired
    public void setUserSessionService(UserSessionService userSessionService) {
        this.staticUserSessionService = userSessionService;
    }

    RESTRequestHeaders headers;
    Object content;

    protected RESTRequestGeneric(RESTRequestHeaders headers, Object content) {
        this.headers = headers;
        this.setContent(content);
    }

    public RESTRequestHeaders getHeaders() {
        return headers;
    }

    public Object getContent() {
        return content;
    }
    public LinkedTreeMap getContentTree() { return (LinkedTreeMap) content; }
    public void setContent(Object content) {
        this.content = content;
    }
}
