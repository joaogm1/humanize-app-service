package humanize.service.dtos.content;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentResponse {

    private String id;
    private String title;
    private String text;
    private String category;
    private String role;

    private Integer trimester;
    private Integer weekRangeStart;
    private Integer weekRangeEnd;

    private String type;

    private String createdAt;
    private String updatedAt;
}
