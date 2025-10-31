package humanize.service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "birth_plans")
public class BirthPlanEntity {
    @Id
    private String id;
    private String userId;
    private String companionName;
    private String companionRelationship;
    private List<String> painReliefMethods;
    private String birthPosition;
    private String cordClamping;
    private String skinToSkin;
    private String breastfeeding;
    private String additionalNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

