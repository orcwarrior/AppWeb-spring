package org.springframework.samples.webflow.user.session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Repository
@Transactional
public class UserSessionDaoDB implements UserSessionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserSession getSessionByUUID(String uuid) {
        return (UserSession) sessionFactory.getCurrentSession().load(UserSession.class, uuid);
    }

    @Override
    public UserSession getSessionByUser(User user) {
        List<UserSession> userSessions = sessionFactory.getCurrentSession().createQuery("from UserSession us where us.user.id = :user_id").setParameter("user_id", user.getId()).list();
        if (userSessions.isEmpty())
            return null;
        else
            return userSessions.get(0);
    }

    @Override
    public boolean saveSession(UserSession userSession) {
        sessionFactory.getCurrentSession().save(userSession);
        return true;
    }

    @Override
    public boolean deleteSession(UserSession userSession) {
        sessionFactory.getCurrentSession().delete(userSession);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    protected List<UserSession> getUserSessions() {
        return sessionFactory.getCurrentSession().createQuery("from UserSession ").list();
    }
}
