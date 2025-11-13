package humanize.service.controllers;

import humanize.service.dtos.content.*;
import humanize.service.services.ContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contents")
@CrossOrigin("*")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<ContentResponse> create(@RequestBody CreateContentRequest request) {
        return ResponseEntity.ok(contentService.createContent(request));
    }

    @GetMapping
    public ResponseEntity<List<ContentResponse>> getAll() {
        return ResponseEntity.ok(contentService.getAllContents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(contentService.getContentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponse> update(@PathVariable String id,
                                                  @RequestBody UpdateContentRequest request) {
        return ResponseEntity.ok(contentService.updateContent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
