package fr.epita.service;

import fr.epita.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactDAO implements IDao<Contact> {
    private final SessionFactory sessionFactory;

    public ContactDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.save(contact);
    }

    @Override
    @Transactional
    public Contact findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Contact.class, id);
    }

    @Override
    @Transactional
    public List<Contact> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Contact", Contact.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.update(contact);
    }

    @Override
    @Transactional
    public void delete(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(contact);
    }
}
