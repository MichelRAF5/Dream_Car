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

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String sobrenome;

    @Column(nullable = false, length = 11)
    private String telefone;

    // Construtores
    public User(String email, String senha, String nome, String sobrenome, String telefone) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
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

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha (String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
