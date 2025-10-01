package humanize.service.services;

import humanize.service.entities.UserEntity;
import humanize.service.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserEntity userEntity) {
        // Validações e lógica de negócio aqui
        System.out.println("Creating user: " + userEntity);
        userRepository.save(userEntity);

        // Aqui você adicionaria a lógica para salvar no banco de dados
        // Por exemplo: userRepository.save(userEntity);
    }

    public UserEntity getUserByLogin(String login) {
        // Lógica para buscar usuário por login
        System.out.println("Searching for user with login: " + login);

        // Por enquanto retorna um usuário mockado
        return UserEntity.builder()
                .id("1")
                .name("User Example")
                .username(login)
                .cpf("000.000.000-00")
                .build();
    }
}
