package org.springframework.samples.webflow.rest.request;

import org.springframework.samples.webflow.rest.request.headers.RESTRequestHeaders;
import org.springframework.samples.webflow.rest.response.RESTResponse;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public interface RESTRequest {
    public boolean validateSession();
    public RESTResponse doAction();
}
