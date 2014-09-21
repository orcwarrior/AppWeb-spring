package org.springframework.samples.webflow.article;


import java.util.List;

/**
 * Created by Dariusz on 2014-05-12.
 */
public interface ArticleDao {
    public List<Article> getArticles();

    public Article getArticleById(int id);

    public boolean saveArticle(Article article);

    public boolean deleteArticle(Article article);

    public boolean modifyArticle(Article article);
}
