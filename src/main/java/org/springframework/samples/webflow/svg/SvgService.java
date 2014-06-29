package org.springframework.samples.webflow.svg;

import org.lesscss.deps.org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by orcwarrior on 2014-06-21.
 */
@Service
public class SvgService {
    private static Logger logger = Logger.getLogger("SvgService");

    public String processSvg(InputStream SvgStream, Integer size, String fill) throws IOException {
        // No nulls!
        size = (size==null)?32:size;
        fill = (fill==null)?"000":fill;

        StringWriter writer = new StringWriter();
        IOUtils.copy(SvgStream, writer, "UTF8");
        String processedSvg = writer.toString();
        logger.info("SVG: " + processedSvg);
        processedSvg = setupSvgSize(processedSvg,size);
        processedSvg = setupSvgFill(processedSvg, fill);
        logger.info("SVG-processed: " + processedSvg);
        return processedSvg;
    }

    private String setupSvgFill(String processedSvg, String fill) {
        // hex color? (served without '#')
        if (fill.matches("^[0-9A-Fa-f]{6}$"))
            fill = "#"+fill;
        processedSvg = processedSvg.replace("<path ","<path fill=\""+fill+"\" ");
        return processedSvg;
    }

    private String setupSvgSize(String input, Integer size)
    {
        Pattern p = Pattern.compile("(<svg.*?width=\")(.*?)(\".*?height=\")(.*?)(\".*?>)");
        Matcher m = p.matcher(input);
        String out = m.replaceFirst("$1"+size.toString()+"$3"+size.toString()+"$5");
        return out.toString();
    }
}
