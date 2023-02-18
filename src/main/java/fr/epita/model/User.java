package fr.epita.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SeenMovie> seenMovies = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", unique = true)
    private Role role;
    // getters and setters

    public User() {
    }
    public User(int id, String username, Contact contact, Set<SeenMovie> seenMovies, Role role) {
        this.id = id;
        this.username = username;
        this.contact = contact;
        this.seenMovies = seenMovies;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<SeenMovie> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<SeenMovie> seenMovies) {
        this.seenMovies = seenMovies;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", contact=" + contact +
                ", seenMovies=" + seenMovies +
                ", role=" + role +
                '}';
    }
}
