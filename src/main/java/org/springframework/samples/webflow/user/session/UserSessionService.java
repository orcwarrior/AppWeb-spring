package org.springframework.samples.webflow.user.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.user.User;
import org.springframework.stereotype.Service;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Service
public class UserSessionService {

    @Autowired
    UserSessionDao userSessionDao;

    public UserSession getUserSessionByUUID(String uuid) {
        return userSessionDao.getSessionByUUID(uuid);
    }
    public UserSession getUserSessionByUser(User user) {
        return userSessionDao.getSessionByUser(user);
    }
    public UserSession generateUserSession(User user) {
        UserSession oldSession = userSessionDao.getSessionByUser(user);
        if (oldSession != null)
            userSessionDao.deleteSession(oldSession);
        UserSession newSession = new UserSession(user);
        userSessionDao.saveSession(newSession);
        return newSession;
    }

    public boolean isValidSessionUUID(String uuid) {
        UserSession session = getUserSessionByUUID(uuid);
        return (session != null) && (session.getUuid().equals(uuid));
    }
}
