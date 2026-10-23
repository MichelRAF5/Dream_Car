package dreamCar.services;

import dreamCar.dto.CadastroDTO;
import dreamCar.entities.Endereco;
import dreamCar.entities.User;
import dreamCar.repositories.EnderecoRepository;
import dreamCar.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService { // As classes Service realizam apenas os métodos das entidades fazendo o uso dos métodos do Repository
    private final UserRepository userRepository;
    private final EnderecoRepository enderecoRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Construtor para o Spring injetar o UserRepository
    public UserService(UserRepository userRepository, EnderecoRepository enderecoRepository) {
        this.userRepository = userRepository;
        this.enderecoRepository = enderecoRepository;
    }

    /* Verifica a existência do email no db
     caso não exista salva o user (senha como hash) e o endereço no db e retorna true,
     caso exista retorna false */
    @Transactional // Garante o rollback caso um dos saves dê errado
    public boolean cadastrar(CadastroDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return false;
        }

        User u = new User(dto.getEmail(), encoder.encode(dto.getSenha()), dto.getNome(), dto.getSobrenome(), dto.getTelefone());
        userRepository.save(u);

        Endereco e = new Endereco(u, dto.getCep(), dto.getRua(), dto.getNumero(), dto.getBairro(), dto.getCidade(), dto.getEstado(), dto.getObservacao());
        enderecoRepository.save(e);

        return true;
    }

    /* Procura um user com o email
    caso ache compara as senhas
    só retorna true se o login for bem-sucedido */
    public boolean login(String email, String senha) {
        Optional<User> resultado = userRepository.findByEmail(email); // Caso ache o usuário armazena o objeto User

        if (resultado.isEmpty()) {
            return false;
        }

        User u = resultado.get();
        return encoder.matches(senha, u.getSenha()); // Compara as senhas e retorna true ou false
    }

    // Verifica se os campos estão vazios e retorna true caso estejam
    public boolean camposInvalidos(String email, String senha) {
        return email == null || email.isBlank() || senha == null || senha.isBlank();
    }

    // Retorna os dados do cadastro
    public CadastroDTO exibirCadastro(String email) {
        User usuario = userRepository.findByEmail(email).orElse(null);

        if (usuario == null) {
            return null;
        }

        Endereco endereco = enderecoRepository.findByUsuario(usuario).orElse(null);

        return new CadastroDTO(usuario, endereco);
    }
}