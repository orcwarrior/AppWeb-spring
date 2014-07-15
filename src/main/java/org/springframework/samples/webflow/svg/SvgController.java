package org.springframework.samples.webflow.svg;

import com.sun.org.apache.xml.internal.utils.URI;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.hsqldb.server.Servlet;
import org.primefaces.webapp.MultipartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.engine.abstractfacesservlet.AbstractFacesServlet;
import org.springframework.samples.webflow.engine.abstractfacesservlet.ConcreteFacesServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.ContextExposingHttpServletRequest;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

/**
 * Created by orcwarrior on 2014-06-20.
 */
@Controller
public class SvgController {

    static public final String SVG_PREFIX = "/SVG/";

    @Autowired
    public SvgService svgService;

    // Needed for resolving faces resources library
    @ManagedProperty("#{facesContext}")
    FacesContext faces;

    @RequestMapping(value = SVG_PREFIX + "**", method = RequestMethod.GET,
            produces = "image/svg+xml")
    public void getSVG(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        ServletContext servletContext = request.getSession().getServletContext();
        SvgURIParser parsedUri = new SvgURIParser(request);
        String resp = svgService.getAndProcessSVG(parsedUri, servletContext);
        response.setContentType("image/svg+xml");
        OutputStream outStream = response.getOutputStream();
        IOUtils.write(resp, outStream);
    }

    public class SvgURIParser {

        static private final int SVG_SIZE_DEFAULT = 32;
        static private final String SVG_FILL_DEFAULT = "000000";
        static private final String SVG_LIBRARY_DEFAULT = "";
        static private final String SVG_VERSION_DEFAULT = "1_0";

        // Properties:
        protected String path;
        protected String fill;
        protected int size;
        protected String library;
        protected String version;

        public SvgURIParser(HttpServletRequest request) {
            String sizeStr;
            path = request.getPathInfo().substring(SvgController.SVG_PREFIX.length());

            fill = request.getParameter("fill");
            if (fill == null) fill = SVG_FILL_DEFAULT;

            sizeStr = request.getParameter("size");
            size = (sizeStr == null) ? SVG_SIZE_DEFAULT : Integer.valueOf(sizeStr);

            library = request.getParameter("lib");
            if (library == null) library = SVG_LIBRARY_DEFAULT;

            version = request.getParameter("v");
            if (version == null) version = SVG_VERSION_DEFAULT;
        }

        public String getPath() {
            return path;
        }

        public String getFill() { return fill; }

        public String getLibrary() {
            return library;
        }
        public int getSize() { return size; }
        public String getVersion() { return version; }
    }

}