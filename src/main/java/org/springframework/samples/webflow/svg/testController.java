package org.springframework.samples.webflow.svg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by orcwarrior on 2014-07-12.
 */
@Controller
public class testController {


    @RequestMapping("/TEST/**")
    public String test(HttpServletRequest request, HttpServletResponse response)
    {
        return "login";
    }
}
