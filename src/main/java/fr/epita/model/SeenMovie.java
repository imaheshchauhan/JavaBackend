package fr.epita.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seen_movie")
public class SeenMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    // getters and setters

    public SeenMovie() {
    }

    public SeenMovie(int id, Date date, Movie movie, User user) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SeenMovie{" +
                "id=" + id +
                ", date=" + date +
                ", movie=" + movie +
                ", user=" + user +
                '}';
    }
}