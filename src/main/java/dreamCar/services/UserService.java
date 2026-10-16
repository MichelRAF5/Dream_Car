package dreamCar.services;

import dreamCar.entities.User;
import dreamCar.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService { // As classes Service realizam apenas os métodos das entidades fazendo o uso dos métodos do Repository
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Construtor para o Spring injetar o UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* Verifica a existência do email no db
     caso não exista salva o email e a senha (como hash) no db e retorna true
     caso exista retorna false */
    public boolean cadastrar(String email, String senha) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }

        User u = new User(email, encoder.encode(senha)); // Bcrypt faz o set da senha como hash
        userRepository.save(u); // Salva o user no db
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
    public boolean camposInvalidos(User user) {
        return user.getEmail() == null || user.getEmail().isBlank() || user.getSenha() == null || user.getSenha().isBlank();
    }
}
