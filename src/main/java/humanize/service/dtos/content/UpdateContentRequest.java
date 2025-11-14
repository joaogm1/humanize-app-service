package humanize.service.dtos.content;

import lombok.Data;

@Data
public class UpdateContentRequest {

    private String title;
    private String text;
    private String category;
    private String role;

    private Integer trimester;
    private Integer weekRangeStart;
    private Integer weekRangeEnd;

    private String type;
}
