package dreamCar.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column
    private String observacao;

    // Construtores
    public Endereco(User usuario, String cep, String rua, String numero, String bairro, String cidade, String estado, String observacao) {
        this.usuario = usuario;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.observacao = observacao;
    }

    public Endereco() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getObservacao() {
        return observacao;
    }

    // Setters
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", idUsuario=" + usuario +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
