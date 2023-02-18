package fr.epita.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "added")
    private Date added;

    @Column(name = "external_id")
    private String externalId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SeenMovie> seenMovies = new HashSet<>();
    // getters and setters

    public Movie() {
    }
    public Movie(int id, String title, Date added, String externalId, Set<SeenMovie> seenMovies) {
        this.id = id;
        this.title = title;
        this.added = added;
        this.externalId = externalId;
        this.seenMovies = seenMovies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Set<SeenMovie> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<SeenMovie> seenMovies) {
        this.seenMovies = seenMovies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", added=" + added +
                ", externalId='" + externalId + '\'' +
                ", seenMovies=" + seenMovies +
                '}';
    }
}

