package humanize.service.repositories;

import humanize.service.entities.ContentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<ContentEntity, String> {

    List<ContentEntity> findByRole(String role);

    List<ContentEntity> findByCategory(String category);

    List<ContentEntity> findByTrimester(Integer trimester);

    List<ContentEntity> findByWeekRangeStartLessThanEqualAndWeekRangeEndGreaterThanEqual(
            Integer weekStart, Integer weekEnd);
}
