package humanize.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBirthPlanRequest {
    private String companionName;
    private String companionRelationship;
    private List<String> painReliefMethods;
    private String birthPosition;
    private String cordClamping;
    private String skinToSkin;
    private String breastfeeding;
    private String additionalNotes;
}

