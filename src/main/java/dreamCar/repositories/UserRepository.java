package dreamCar.repositories;

import dreamCar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // JpaRepository<'entidade que o repository gerencia' e 'tipo da @Id'>
    // A interface herda os métodos do JpaRepository (como save, findById, findAll...) e os métodos específicos da entidade são declarados

    Optional<User> findByEmail(String email); // Procura o User por email retornando um objeto Optional(com o User) ou Optional.empty()

    boolean existsByEmail(String email); // Verifica existência do User pelo email e retorna boolean
}
