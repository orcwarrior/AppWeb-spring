package org.springframework.samples.webflow.svg;

import org.lesscss.deps.org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by orcwarrior on 2014-06-20.
 */
@Controller
public class SvgController {


    @Autowired
    public SvgService svgService;

    @RequestMapping(value="/SVG:{svgName}.svg", method = RequestMethod.GET,
            produces = "image/svg+xml")
    public void getSVG(@PathVariable("svgName") String svgName,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        ServletContext context = request.getSession().getServletContext();
        Integer size = Integer.valueOf(request.getParameter("size"));
        String fill =  request.getParameter("fill");
        InputStream inStream = context.getResourceAsStream("/img/ui/"+svgName+".svg");
        String resp = svgService.processSvg(inStream,size,fill);

        response.setContentType("image/svg+xml");
        OutputStream outStream = response.getOutputStream();
        IOUtils.write(resp,outStream);
    }


    @RequestMapping("testbardzo")
    public String test()
    {
        return "login";
    }
}
