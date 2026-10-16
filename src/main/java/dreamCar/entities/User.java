package dreamCar.entities;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable=false, unique = true, length = 255)
    private String email;

    @Column(nullable=false, length = 255)
    private String senha;

    // Construtores
    public User(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public User() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha (String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
