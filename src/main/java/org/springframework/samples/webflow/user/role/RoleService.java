package org.springframework.samples.webflow.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by orcwarrior on 2014-09-21.
 */
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public Role getDefaultUsersRole() {
        if (roleDao.getRoleByName("ROLE_USER") == null) {
            Role baseRole = new Role("ROLE_USER");
            roleDao.saveRole(baseRole);
        }
        return roleDao.getRoleByName("ROLE_USER");
    }
}
