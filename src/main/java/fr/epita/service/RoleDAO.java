package fr.epita.service;

import fr.epita.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleDAO implements IDao<Role> {

    private SessionFactory sessionFactory;

    public RoleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Override
    @Transactional
    public Role findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }

}
