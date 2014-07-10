package org.springframework.samples.webflow.user;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.news.Article;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by orcwarrior on 2014-07-09.
 */
@Repository
@Transactional
public class UserDaoDB implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<User> getUsers()
    {
        return sessionFactory.getCurrentSession().createQuery("FROM users").list();
    }

    @Override
    public User getUserByName(String username) {
        return (User) sessionFactory.getCurrentSession().load(User.class, username);
    }

    @Override
    public boolean saveUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        this.sessionFactory.getCurrentSession().delete(user);
        return true;
    }

    @Override
    public boolean modifyUser(User user) {
        this.sessionFactory.getCurrentSession().update(user);
        return true;
    }
}
