package org.springframework.samples.webflow.svg;

import com.sun.faces.context.FacesContextFactoryImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.*;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by orcwarrior on 2014-06-21.
 */
@Service
public class SvgService {
    private static Logger logger = Logger.getLogger("SvgService");

    private String[] jsfResourceLibraries;
    public SvgService() {

       // jsfResourceLibraries = getJsfResourceSubdirectories();
    }

    public String getAndProcessSVG(SvgController.SvgURIParser uri, ServletContext servletContext) throws IOException {

        //C:\Users\orcwarrior\InteliJ Projects\AppWeb-spring\src\main\webapp\resources\news\1_0\img\bold.svgName
        // String requestURI = "/app/javax.faces.resource" + uri.getPath() + (uri.getLibrary().isEmpty() ? "" : ("?In=" + url.get));

        Set resourcePaths = servletContext.getResourcePaths("/cog.svg");
        String contextPath = servletContext.getContextPath();
        String svgPath = getSVGPath(contextPath, uri.getPath(),uri.getLibrary(),uri.getVersion());
        InputStream inStream = servletContext.getResourceAsStream(svgPath);
        //PrimeResource svgName = new PrimeResource(new javax.faces.application.Resource());

        StringWriter writer = new StringWriter();
        IOUtils.copy(inStream, writer, "UTF8");
        String processedSvg = writer.toString();
        processedSvg = setupSvgSize(processedSvg,uri.getSize());
        processedSvg = setupSvgFill(processedSvg, uri.getFill());
        logger.info("Generate SVG, fill: "+ uri.getFill() + ", Size: "+ uri.getSize());
        return processedSvg;
    }

    private String setupSvgFill(String processedSvg, String fill) {
        if (fill.matches("^[0-9A-Fa-f]{6}$"))
            fill = "#"+fill;
        processedSvg = processedSvg.replace("<path ","<path fill=\""+fill+"\" ");
        return processedSvg;
    }

    private String setupSvgSize(String input, Integer size)
    {
            Pattern p = Pattern.compile("(<svg.*?width=\")(.*?)(\".*?height=\")(.*?)(\".*?>)");
            Matcher m = p.matcher(input);
            String out = m.replaceFirst("$1" + size.toString() + "$3" + size.toString() + "$5");
            return out.toString();
    }

    private String getSVGPath(String contextPath, String svgName, String library, String version) throws FileNotFoundException {
        String libFullPath = contextPath + "/resources/";
        libFullPath += (library.isEmpty() ? "" : library + "/");
        libFullPath += (version.isEmpty() ? "" : version + "/");
        libFullPath += svgName;
        return libFullPath;
    }
    private String getJsfResourcesAbsolutePath(){
        // TODO: Optimize, make it an property
        String relativeResourcePath = "/resources/";
        FacesContext context = FacesContext.getCurrentInstance();
        if (context instanceof FacesContext) {
            String absoluteDiskPath = context.getExternalContext().getRealPath(relativeResourcePath);
            return absoluteDiskPath;
        }
        return "";
    }

}
