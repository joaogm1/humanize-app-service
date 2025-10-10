package humanize.service.repositories;

import humanize.service.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByCpf(String cpf);
    boolean existsByUsername(String username);
    boolean existsByCpf(String cpf);
}