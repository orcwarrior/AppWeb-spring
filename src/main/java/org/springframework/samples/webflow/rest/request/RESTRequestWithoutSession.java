package org.springframework.samples.webflow.rest.request;

import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public abstract class RESTRequestWithoutSession extends RESTRequestGeneric {
    protected RESTRequestWithoutSession(RESTRequestHeaders headers, Object content) {
        super(headers, content);
    }

    @Override
    public boolean validateSession() {
        return true;
    }


}
