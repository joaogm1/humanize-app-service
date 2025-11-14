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
    // GET por role
@GetMapping("/role/{role}")
public ResponseEntity<List<ContentResponse>> getByRole(@PathVariable String role) {
    return ResponseEntity.ok(contentService.getByRole(role));
}

// GET por categoria
@GetMapping("/category/{category}")
public ResponseEntity<List<ContentResponse>> getByCategory(@PathVariable String category) {
    return ResponseEntity.ok(contentService.getByCategory(category));
}

// GET por trimestre
@GetMapping("/trimester/{trimester}")
public ResponseEntity<List<ContentResponse>> getByTrimester(@PathVariable Integer trimester) {
    return ResponseEntity.ok(contentService.getByTrimester(trimester));
}

// GET por semana gestacional (busca range)
@GetMapping("/week/{week}")
public ResponseEntity<List<ContentResponse>> getByWeek(@PathVariable Integer week) {
    return ResponseEntity.ok(contentService.getByWeek(week));
}

}
