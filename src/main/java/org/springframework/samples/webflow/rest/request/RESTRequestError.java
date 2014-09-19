package org.springframework.samples.webflow.rest.request;

/**
 * Created by orcwarrior on 2014-09-15.
 */
public class RESTRequestError {
    private int statusCode;
    private String error;

    public RESTRequestError(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
