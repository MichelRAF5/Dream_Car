package dreamCar.controllers;

import dreamCar.dto.CadastroDTO;
import dreamCar.entities.Endereco;
import dreamCar.entities.User;
import dreamCar.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500",
        "http://localhost:63342"
})
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // Construtor para o Spring injetar o UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (userService.camposInvalidos(user.getEmail(), user.getSenha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email e senha são obrigatórios");
        }

        if (userService.login(user.getEmail(), user.getSenha())) {
            return new ResponseEntity<>("Login efetuado com sucesso", HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha errados");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody CadastroDTO dto) {
        if (userService.camposInvalidos(dto.getEmail(), dto.getSenha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email e senha são obrigatórios");
        }

        if (userService.cadastrar(dto)) {
            return new ResponseEntity<>("Registro cadastrado com sucesso", HttpStatus.CREATED);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já registrado no sistema");
    }

    @GetMapping("/perfil")
    public ResponseEntity<CadastroDTO> perfil(@RequestParam String email) {
        CadastroDTO dto = userService.exibirCadastro(email);

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(dto);
    }
}
