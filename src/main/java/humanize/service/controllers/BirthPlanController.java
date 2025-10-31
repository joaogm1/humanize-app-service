package humanize.service.controllers;

import humanize.service.dtos.BirthPlanResponse;
import humanize.service.dtos.CreateBirthPlanRequest;
import humanize.service.dtos.UpdateBirthPlanRequest;
import humanize.service.services.BirthPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/birth-plans")
@AllArgsConstructor
public class BirthPlanController {

    private final BirthPlanService birthPlanService;

    @PostMapping
    public ResponseEntity<?> createBirthPlan(@RequestBody CreateBirthPlanRequest request) {
        try {
            BirthPlanResponse response = birthPlanService.createBirthPlan(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBirthPlanById(@PathVariable String id) {
        try {
            BirthPlanResponse response = birthPlanService.getBirthPlanById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Plano de parto não encontrado"));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBirthPlanByUserId(@PathVariable String userId) {
        try {
            BirthPlanResponse response = birthPlanService.getBirthPlanByUserId(userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Plano de parto não encontrado para este usuário"));
        }
    }

    @GetMapping
    public ResponseEntity<List<BirthPlanResponse>> getAllBirthPlans() {
        List<BirthPlanResponse> responses = birthPlanService.getAllBirthPlans();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBirthPlan(@PathVariable String id, 
                                              @RequestBody UpdateBirthPlanRequest request) {
        try {
            BirthPlanResponse response = birthPlanService.updateBirthPlan(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Erro ao atualizar plano de parto"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBirthPlan(@PathVariable String id) {
        try {
            birthPlanService.deleteBirthPlan(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Plano de parto não encontrado"));
        }
    }

    @lombok.Data
    @lombok.AllArgsConstructor
    private static class ErrorResponse {
        private String message;
    }
}
