package fr.epita.service;

import fr.epita.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressDAO implements IDao<Address> {
    private SessionFactory sessionFactory;

    public AddressDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.save(address);
    }

    @Override
    @Transactional
    public Address findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Address.class, id);
    }

    @Override
    @Transactional
    public List<Address> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Address", Address.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.update(address);
    }

    @Override
    @Transactional
    public void delete(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(address);
    }
}

