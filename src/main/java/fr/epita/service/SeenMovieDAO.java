package fr.epita.service;

import fr.epita.model.SeenMovie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeenMovieDAO implements IDao<SeenMovie> {
    private SessionFactory sessionFactory;

    public SeenMovieDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(SeenMovie seenMovie) {
        Session session = sessionFactory.getCurrentSession();
        session.save(seenMovie);
    }

    @Override
    @Transactional
    public SeenMovie findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(SeenMovie.class, id);
    }

    @Override
    @Transactional
    public List<SeenMovie> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM SeenMovie", SeenMovie.class).getResultList();
    }

    @Override
    @Transactional
    public void update(SeenMovie seenMovie) {
        Session session = sessionFactory.getCurrentSession();
        session.update(seenMovie);
    }

    @Override
    @Transactional
    public void delete(SeenMovie seenMovie) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(seenMovie);
    }
}
