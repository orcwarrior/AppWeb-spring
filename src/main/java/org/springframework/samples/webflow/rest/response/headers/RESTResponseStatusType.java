package org.springframework.samples.webflow.rest.response.headers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public enum RESTResponseStatusType {
    @SerializedName("RESTResponseStatusType.success")
    success,
    @SerializedName("RESTResponseStatusType.fail")
    fail
}
