package humanize.service.services;

import humanize.service.dtos.content.*;
import humanize.service.entities.ContentEntity;
import humanize.service.repositories.ContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    // CREATE
    public ContentResponse createContent(CreateContentRequest request) {

        ContentEntity content = ContentEntity.builder()
                .title(request.getTitle())
                .text(request.getText())
                .category(request.getCategory())
                .role(request.getRole())
                .trimester(request.getTrimester())
                .weekRangeStart(request.getWeekRangeStart())
                .weekRangeEnd(request.getWeekRangeEnd())
                .type(request.getType())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        contentRepository.save(content);

        return toResponse(content);
    }

    // READ ALL
    public List<ContentResponse> getAllContents() {
        return contentRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // READ ONE
    public ContentResponse getContentById(String id) {
        ContentEntity content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        return toResponse(content);
    }

    // UPDATE
    public ContentResponse updateContent(String id, UpdateContentRequest request) {
        ContentEntity content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        content.setTitle(request.getTitle());
        content.setText(request.getText());
        content.setCategory(request.getCategory());
        content.setRole(request.getRole());
        content.setTrimester(request.getTrimester());
        content.setWeekRangeStart(request.getWeekRangeStart());
        content.setWeekRangeEnd(request.getWeekRangeEnd());
        content.setType(request.getType());
        content.setUpdatedAt(LocalDateTime.now());

        contentRepository.save(content);

        return toResponse(content);
    }

    // DELETE
    public void deleteContent(String id) {
        contentRepository.deleteById(id);
    }

    private ContentResponse toResponse(ContentEntity entity) {
        return ContentResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .text(entity.getText())
                .category(entity.getCategory())
                .role(entity.getRole())
                .trimester(entity.getTrimester())
                .weekRangeStart(entity.getWeekRangeStart())
                .weekRangeEnd(entity.getWeekRangeEnd())
                .type(entity.getType())
                .createdAt(entity.getCreatedAt().toString())
                .updatedAt(entity.getUpdatedAt().toString())
                .build();
    }
}
