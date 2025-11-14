
package humanize.service.dtos.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContentRequest {

    private String title;
    private String text;
    private String category;
    private String role;
    private Integer trimester;
    private Integer weekRangeStart;
    private Integer weekRangeEnd;
    private String type;
}

