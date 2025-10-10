package humanize.service.services;

import humanize.service.dtos.CreateUserRequest;
import humanize.service.dtos.UserResponse;
import humanize.service.entities.UserEntity;
import humanize.service.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request) {
        // Validação 1: Username já existe?
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username já está em uso!");
        }

        // Validação 2: CPF já existe?
        if (userRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("CPF já está cadastrado!");
        }

        // Validação 3: Campos obrigatórios
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório!");
        }
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username é obrigatório!");
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new RuntimeException("Senha deve ter no mínimo 6 caracteres!");
        }
        if (request.getCpf() == null || !isValidCpf(request.getCpf())) {
            throw new RuntimeException("CPF inválido!");
        }

        // Criar entidade
        UserEntity userEntity = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(request.getPassword()) // TODO: Criptografar depois!
                .cpf(request.getCpf())
                .build();

        // Salvar no banco
        UserEntity savedUser = userRepository.save(userEntity);

        System.out.println("Usuário criado com sucesso: " + savedUser.getUsername());

        // Retornar resposta sem senha
        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .username(savedUser.getUsername())
                .cpf(savedUser.getCpf())
                .build();
    }

    public UserResponse getUserByLogin(String login) {
        UserEntity user = userRepository.findByUsername(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .cpf(user.getCpf())
                .build();
    }

    //Validação básica de CPF
    private boolean isValidCpf(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se não são todos iguais (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return true;
        // Para validação completa com dígitos verificadores, seria mais complexo
    }
}