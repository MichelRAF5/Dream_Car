package dreamCar.repositories;

import dreamCar.entities.Endereco;
import dreamCar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByUsuario(User user);
}
