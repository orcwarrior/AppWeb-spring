package org.springframework.samples.webflow.rest.response.headers;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public class RESTResponseStatus {
    RESTResponseStatusType type;
    String message;

    public RESTResponseStatus(RESTResponseStatusType type, String message) {
        this.type = type;
        this.message = message;
    }

    public RESTResponseStatus(RESTResponseStatusType type) {
        this.type = type;
    }
}
