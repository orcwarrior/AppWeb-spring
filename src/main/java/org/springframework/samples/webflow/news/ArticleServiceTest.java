package org.springframework.samples.webflow.news;

import junit.framework.TestCase;

/**
 * Created by orcwarrior on 2014-07-01.
 */
public class ArticleServiceTest extends TestCase {
    public void testArticleUpdateContentLinks() throws Exception {

        String exceptedResults = "<a data-hover=\"Test\" href=\"a\">Test</a>\n" +
                                 "<a data-hover=\"bfdfdf\"  href=\"b\">bfdfdf</a>\n" +
                                 "<a data-hover=\"not-b\" href=\"#aasd\" >not-b</a>";
        String in = "<a href=\"a\">Test</a>\n" +
                    "<a data-hover=\"bfdfdf\" href=\"b\">bfdfdf</a>\n" +
                    "<a href=\"#aasd\" data-hover=\"#aasd\">not-b</a>";
        String out = new ArticleService().articleUpdateContentLinks(in);

        assertEquals(out, exceptedResults);

        // fail("results don't equals excepted results!");
    }
}
