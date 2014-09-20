package org.springframework.samples.webflow.rest.request;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.springframework.samples.webflow.rest.response.RESTResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by orcwarrior on 2014-09-15.
 */
@Service
public class RESTRequestService {
    static private RESTRequestError checkRESTRequest(HttpServletRequest request) {
        if (request.getMethod() != "POST") {
            return new RESTRequestError(HttpStatus.SC_BAD_REQUEST, "Only POST requests are supported!");
        }
        return null;
    }

    static public HttpServletResponse processRequest(HttpServletRequest request, String body, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        RESTRequestError error = checkRESTRequest(request);
        if (error != null) {
            response.sendError(error.getStatusCode(), error.getError());
        }
        RESTRequestGenericImpl restRequestGeneric = gson.fromJson(body, RESTRequestGenericImpl.class);
        String jsonContent = gson.toJson(restRequestGeneric.content);
        RESTRequest requestConcrete = RESTRequestFactory.buildConcreteRequest(restRequestGeneric);
        RESTResponse restResponse = requestConcrete.doAction();
        response.getWriter().write(gson.toJson(restResponse));
        return response;
    }
}
