package org.springframework.samples.webflow.user.role;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by orcwarrior on 2014-09-21.
 */
@Repository
@Transactional
public class RoleDaoDB implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Role> getRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }

    @Override
    public Role getRoleByName(String roleName) {
        List<Role> roles = sessionFactory.getCurrentSession().createQuery("from Role r where r.role = :rolename").setParameter("rolename", roleName).list();
        if (roles.isEmpty())
            return null;
        else
            return roles.get(0);
    }

    @Override
    public boolean saveRole(Role role) {
        this.sessionFactory.getCurrentSession().save(role);
        return true;
    }

    @Override
    public boolean deleteRole(Role role) {
        this.sessionFactory.getCurrentSession().delete(role);
        return true;
    }
}
