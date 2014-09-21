package org.springframework.samples.webflow.user.role;

import java.util.List;

/**
 * Created by orcwarrior on 2014-09-21.
 */
public interface RoleDao {
    public List<Role> getRoles();

    public Role getRoleByName(String name);

    public boolean saveRole(Role Role);

    public boolean deleteRole(Role Role);
}
