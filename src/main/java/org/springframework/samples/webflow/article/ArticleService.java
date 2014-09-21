package org.springframework.samples.webflow.article;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by orcwarrior on 2014-07-01.
 */
@Service
public class ArticleService implements Serializable {

    public String articleContentProcessChain(String in)
    {
        in = articleUpdateContentLinks(in);
        return in;
    }

    public String articleUpdateContentLinks(String in) { // ads data-hover="$a-content" as <a> tag attribute
        // 1. Remove all founded data-hover attributes in a tags:
        String out = in.replaceAll("(<a .*?)(data-hover=\".*?\")(.*?>.*?</a>)", "$1$3" );

        // 2. Add updated data-hover params
        Matcher m = Pattern.compile("<a (.*?>)(.*?)(</a>)")
                .matcher(out);
        while (m.find()) {
            String foundString = m.group();
            String updatedString = new String(foundString);
            // adds data-hover with proper value:
            String updatedAttributes = m.group(1);
            updatedAttributes = "data-hover=\"" + m.group(2) + "\" " + updatedAttributes;
            updatedString = updatedString.replace(m.group(1), updatedAttributes);

            out = out.replace(foundString, updatedString);
        }
        return out;
    }
}
