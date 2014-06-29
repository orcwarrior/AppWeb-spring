package org.springframework.samples.webflow.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

    @ManagedProperty(value = "#{ArticleController}")
    public ArticleController articleController;

    public String articleTitle;

    public String articleContent;



    @PostConstruct
    public void init() {
        // You can do here your initialization thing based on managed properties, if necessary.
    }
    public Article[] getArticles()
    {
        // TODO: Autowiring doesn't work, fix
       List<Article> artList = articleDao.getArticles();

       return artList.toArray(new Article[artList.size()]);
    }

    public void modifyArticle() {

        String paramArticleID =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleID");
        Integer articleID = Integer.valueOf(paramArticleID);

        logger.info("ArticleID: "+articleID);
        logger.info("Article-title: "+articleTitle);
        logger.info("Article-content: "+articleContent);
        articleDao.modifyArticle(new Article(articleID, articleTitle,articleContent));
    }
    public String getInput2() {
        return "hello-franz";
    }


    // Getters/Setters

    public ArticleController getArticleController() {
        return articleController;
    }
    public void setArticleController(ArticleController articleController) {
        this.articleController = articleController;
    }
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
