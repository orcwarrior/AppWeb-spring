package org.springframework.samples.webflow.rest.request;

import com.google.gson.internal.LinkedTreeMap;
import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public abstract class RESTRequestGeneric implements RESTRequest {
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
