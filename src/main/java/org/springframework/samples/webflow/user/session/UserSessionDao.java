package org.springframework.samples.webflow.user.session;

import org.springframework.samples.webflow.user.User;

/**
 * Created by orcwarrior on 2014-09-18.
 */
public interface UserSessionDao {

    public UserSession getSessionByUUID(String uuid);

    public UserSession getSessionByUser(User user);

    public boolean saveSession(UserSession userSession);

    public boolean deleteSession(UserSession userSession);
}
