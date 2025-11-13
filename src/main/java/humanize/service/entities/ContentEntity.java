package humanize.service.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "contents")
public class ContentEntity {

    @Id
    private String id;

    private String title;
    private String text;
    private String category;
    private String role; // gestante, acompanhante, tentante, profissional, todos

    private Integer trimester; // 1,2,3 ou null
    private Integer weekRangeStart;
    private Integer weekRangeEnd;

    private String type; // dica, alerta, informativo, explicacao

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
