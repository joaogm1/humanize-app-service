package humanize.service.dtos;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String username;
    private String password;
    private String cpf;
    private String role; // <-- enviado no cadastro
}
