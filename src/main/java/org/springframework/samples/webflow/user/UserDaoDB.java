package org.springframework.samples.webflow.user;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.news.Article;
import org.springframework.samples.webflow.user.session.UserSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getUserByName(String username) {

        List<User> users = sessionFactory.getCurrentSession().createQuery("from User u where u.username = :username").setParameter("username", username).list();
        if (users.isEmpty())
            return null;
        else
            return users.get(0);
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
