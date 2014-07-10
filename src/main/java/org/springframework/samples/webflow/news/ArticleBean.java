package org.springframework.samples.webflow.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.svg.SvgService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Dariusz on 2014-05-20.
 */
@Component
@ManagedBean(name = "articleBean")
@ViewScoped
public class ArticleBean implements Serializable{

    private static Logger logger = Logger.getLogger("AuthenticationEventListener");

    @Autowired
    public ArticleDao articleDao;
    public void setArticleDao(ArticleDao articleDao) { this.articleDao = articleDao;}
    public ArticleDao getArticleDao() { return this.articleDao;}

    @Inject
    public ArticleService articleService;

    public String articleTitle;

    public String articleContent;



    @PostConstruct
    public void init() {
        // You can do here your initialization thing based on managed properties, if necessary.
    }
    public Article[] getArticles()
    {
       List<Article> artList = articleDao.getArticles();
       return artList.toArray(new Article[artList.size()]);
    }

    public void modifyArticle() {

        String paramArticleID =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleID");
        Integer articleID = Integer.valueOf(paramArticleID);

        // process article-contents links:
        articleContent = articleService.articleContentProcessChain(articleContent);
        logger.info("ArticleID: "+articleID);
        logger.info("Article-title: "+articleTitle);
        logger.info("Article-content: "+articleContent);
        articleDao.modifyArticle(new Article(articleID, articleTitle,articleContent));
    }
    public String getInput2() {
        return "hello-franz";
    }


    // Getters/Setters
    public String getArticleTitle() {
        return articleTitle;
    }
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    public String getArticleContent() {
        return articleContent;
    }
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}
