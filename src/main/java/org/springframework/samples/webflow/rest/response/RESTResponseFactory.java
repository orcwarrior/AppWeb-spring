package org.springframework.samples.webflow.rest.response;

import org.springframework.samples.webflow.rest.response.headers.RESTResponseHeaders;
import org.springframework.samples.webflow.rest.response.headers.RESTResponseStatus;
import org.springframework.samples.webflow.rest.response.headers.RESTResponseStatusType;
import org.springframework.samples.webflow.user.session.UserSession;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public class RESTResponseFactory {
    public static RESTResponse createRESTResponse(UserSession session, Object content) {
        RESTResponse response = new RESTResponse();
        response.setContent(content);
        response.setHeaders(new RESTResponseHeaders(
                new RESTResponseStatus(RESTResponseStatusType.success),
                session));
        return response;
    }
    public static RESTResponse createRESTResponse(String errorMsg) {
        RESTResponse response = new RESTResponse();
        response.setHeaders(new RESTResponseHeaders(
                new RESTResponseStatus(RESTResponseStatusType.fail),
                null));
        return response;
    }
}
