package org.springframework.samples.webflow.user;

import org.springframework.samples.webflow.user.User;

import java.util.List;

/**
 * Created by orcwarrior on 2014-07-09.
 */
public interface UserDao {
    public List<User> getUsers();

    public User getUserByName(String name);

    public boolean saveUser(User user);

    public boolean deleteUser(User user);

    public boolean modifyUser(User user);
}
