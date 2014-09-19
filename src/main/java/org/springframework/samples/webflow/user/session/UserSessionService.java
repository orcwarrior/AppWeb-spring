package org.springframework.samples.webflow.user.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.webflow.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by orcwarrior on 2014-09-18.
 */
@Service
@Component
public class UserSessionService {

    @Autowired
    UserSessionDao userSessionDao;

    public UserSession getUserSessionByUUID(UUID uuid) {
        return userSessionDao.getSessionByUUID(uuid);
    }
    public UserSession generateUserSession(User user) {
        UserSession oldSession = userSessionDao.getSessionByUser(user);
        userSessionDao.deleteSession(oldSession);
        UserSession newSession = new UserSession(user);
        userSessionDao.saveSession(newSession);
        return newSession;
    }

    public boolean isValidSessionUUID(UUID uuid) {
        UserSession session = getUserSessionByUUID(uuid);
        return (session != null) && (session.getUuid() == uuid);
    }
}
