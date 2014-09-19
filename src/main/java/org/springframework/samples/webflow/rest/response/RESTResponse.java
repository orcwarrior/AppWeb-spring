package org.springframework.samples.webflow.rest.response;

import org.springframework.samples.webflow.rest.response.headers.RESTResponseHeaders;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public class RESTResponse {
    RESTResponseHeaders headers;
    Object content;

    public void setContent(Object content) {
        this.content = content;
    }

    public void setHeaders(RESTResponseHeaders headers) {
        this.headers = headers;
    }
}
