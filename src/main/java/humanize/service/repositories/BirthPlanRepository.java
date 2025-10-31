package humanize.service.repositories;

import humanize.service.entities.BirthPlanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BirthPlanRepository extends MongoRepository<BirthPlanEntity, String> {
    Optional<BirthPlanEntity> findByUserId(String userId);
}

