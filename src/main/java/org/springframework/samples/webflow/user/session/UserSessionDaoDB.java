package org.springframework.samples.webflow.user.session;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Repository
@Transactional
public class UserSessionDaoDB implements UserSessionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserSession getSessionByUUID(UUID uuid) {
        return (UserSession) sessionFactory.getCurrentSession().load(UserSession.class, uuid);
    }

    //@Query("SELECT t FROM Token t WHERE LOWER(t.user) = LOWER(:user)")
    //public Token find(@Param("user") String user);
    @Override
    public UserSession getSessionByUser(User user) {

        Query query = sessionFactory.getCurrentSession().createQuery("SELECT us from user_sessions WHERE us.user LIKE :userid")
                .setParameter("userid", user.getId());
        List queryResults = query.list();
        if (queryResults.size()==1)
            return (UserSession) queryResults.get(0);
        return null;
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
}
