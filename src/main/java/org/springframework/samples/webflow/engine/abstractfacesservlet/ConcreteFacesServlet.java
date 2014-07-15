package org.springframework.samples.webflow.engine.abstractfacesservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by orcwarrior on 2014-07-12.
 */
public class ConcreteFacesServlet extends AbstractFacesServlet {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("GET"))
            doGet(request,response);
        else if(request.getRequestURI().startsWith("POST"))
            doPost(request,response);
    }
}
