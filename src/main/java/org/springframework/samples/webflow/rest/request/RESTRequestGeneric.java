package org.springframework.samples.webflow.rest.request;

import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public abstract class RESTRequestGeneric implements RESTRequest {
    RESTRequestHeaders headers;
    Object content;

    protected RESTRequestGeneric(RESTRequestHeaders headers, Object content) {
        this.headers = headers;
        this.content = content;
    }

    public RESTRequestHeaders getHeaders() {
        return headers;
    }

    public Object getContent() {
        return content;
    }
}
