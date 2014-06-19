package org.springframework.samples.webflow.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Dariusz on 2014-05-12.
 */
@Controller
public class ArticleController {
    @Autowired
    public ArticleDao articleDao;
    public void setArticleDao(ArticleDao articleDao) { this.articleDao = articleDao;}

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String listPerson(ModelMap map) {

        map.addAttribute("article", new Article("Tytul nowego artykulu","Nowy artykul"));
        map.addAttribute("articles", articleDao.getArticles());

        return "news";
    }

    @RequestMapping(value = "/addnews", method = RequestMethod.POST)
    public String addArticle(
            @ModelAttribute(value = "article") Article article,
            BindingResult result) {
        articleDao.saveArticle(article);
        return "redirect:/news";
    }

    @RequestMapping("/deletenews/{articleId}")
    public String deleteArticle(@PathVariable("articleId") Integer articleId) {
        articleDao.deleteArticle(new Article(articleId, "", ""));
        return "redirect:/news";
    }

    @RequestMapping(value="/modifynews/{articleId}", method = RequestMethod.POST)
    public String modifyArticle(@PathVariable("articleId") Integer articleId) {
        int a = 4; a+=articleId;
        articleId = a;
        return "a";
    }

//    @RequestBody -Covert Json object to java
//    @ResponseBody-convert Java object to json
//    @RequestMapping(value = "/modifynews/{articleId}" , method = RequestMethod.POST)
//    public @ResponseBody Article modifyArticle(@RequestBody Article jsonString) {
//        // TODO: Seciurity check
//
//        Article article = articleDao.getArticleById(jsonString.getId());
//        article = jsonString;
//        return article;
//    }
}
