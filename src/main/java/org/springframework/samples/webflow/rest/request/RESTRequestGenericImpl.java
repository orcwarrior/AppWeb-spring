package org.springframework.samples.webflow.rest.request;

import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.rest.response.RESTResponse;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public class RESTRequestGenericImpl extends RESTRequestGeneric {

    protected RESTRequestGenericImpl(RESTRequestHeaders headers, Object content) {
        super(headers, content);
    }

    @Override
    public boolean validateSession() {
        return false;
    }

    @Override
    public RESTResponse doAction() {
        return null;
    }
}
