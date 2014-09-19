package org.springframework.samples.webflow.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.rest.request.RESTRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by orcwarrior on 2014-09-15.
 */

@Controller
public class RESTServiceController {

    @Autowired
    RESTRequestService restRequestService;

    @RequestMapping(value = "/api/",
            produces = "application/json")
    public void RESTApiReqeuest(@RequestBody String body, HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response = restRequestService.processRequest(request, body, response);

    }
    
}
