package org.springframework.samples.webflow.article;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dariusz on 2014-05-12.
 */
@Repository
@Transactional
public class ArticleDaoDB implements ArticleDao, Serializable {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void setSessionFactory(SessionFactory sessionFactory)
    { this.sessionFactory = sessionFactory;}

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Article> getArticles()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM Article").list();
    }

    @Override
    @Transactional
    public Article getArticleById(int id) {
        return (Article) sessionFactory.getCurrentSession().load(Article.class, id);
    }

    @Override
    @Transactional
    public boolean saveArticle(Article article) {
        this.sessionFactory.getCurrentSession().save(article);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteArticle(Article article) {
        this.sessionFactory.getCurrentSession().delete(article);
        return true;
    }

    @Override
    @Transactional
    public boolean modifyArticle(Article article) {
        this.sessionFactory.getCurrentSession().update(article);
        return true;
    }


}
