package fr.epita.service;

import fr.epita.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieDAO implements IDao<Movie> {
    private SessionFactory sessionFactory;

    public MovieDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        session.save(movie);
    }

    @Override
    @Transactional
    public Movie findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Movie.class, id);
    }

    @Override
    @Transactional
    public List<Movie> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Movie", Movie.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        session.update(movie);
    }

    @Override
    @Transactional
    public void delete(Movie movie) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(movie);
    }
}
