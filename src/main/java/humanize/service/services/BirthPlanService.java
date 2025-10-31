package humanize.service.services;

import humanize.service.dtos.BirthPlanResponse;
import humanize.service.dtos.CreateBirthPlanRequest;
import humanize.service.dtos.UpdateBirthPlanRequest;
import humanize.service.entities.BirthPlanEntity;
import humanize.service.entities.UserEntity;
import humanize.service.repositories.BirthPlanRepository;
import humanize.service.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BirthPlanService {

    private final BirthPlanRepository birthPlanRepository;
    private final UserRepository userRepository;

    public BirthPlanResponse createBirthPlan(CreateBirthPlanRequest request) {
        // Validar se o usuário existe
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Criar entidade
        BirthPlanEntity birthPlan = BirthPlanEntity.builder()
                .userId(request.getUserId())
                .companionName(request.getCompanionName())
                .companionRelationship(request.getCompanionRelationship())
                .painReliefMethods(request.getPainReliefMethods())
                .birthPosition(request.getBirthPosition())
                .cordClamping(request.getCordClamping())
                .skinToSkin(request.getSkinToSkin())
                .breastfeeding(request.getBreastfeeding())
                .additionalNotes(request.getAdditionalNotes())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Salvar no banco
        BirthPlanEntity savedBirthPlan = birthPlanRepository.save(birthPlan);

        // Retornar resposta
        return mapToResponse(savedBirthPlan, user.getName());
    }

    public BirthPlanResponse getBirthPlanById(String id) {
        BirthPlanEntity birthPlan = birthPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano de parto não encontrado"));

        UserEntity user = userRepository.findById(birthPlan.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return mapToResponse(birthPlan, user.getName());
    }

    public BirthPlanResponse getBirthPlanByUserId(String userId) {
        BirthPlanEntity birthPlan = birthPlanRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Plano de parto não encontrado para este usuário"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return mapToResponse(birthPlan, user.getName());
    }

    public List<BirthPlanResponse> getAllBirthPlans() {
        List<BirthPlanEntity> birthPlans = birthPlanRepository.findAll();

        return birthPlans.stream()
                .map(birthPlan -> {
                    UserEntity user = userRepository.findById(birthPlan.getUserId())
                            .orElse(null);
                    String userName = user != null ? user.getName() : "Usuário não encontrado";
                    return mapToResponse(birthPlan, userName);
                })
                .collect(Collectors.toList());
    }

    public BirthPlanResponse updateBirthPlan(String id, UpdateBirthPlanRequest request) {
        BirthPlanEntity birthPlan = birthPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano de parto não encontrado"));

        // Atualizar campos
        birthPlan.setCompanionName(request.getCompanionName());
        birthPlan.setCompanionRelationship(request.getCompanionRelationship());
        birthPlan.setPainReliefMethods(request.getPainReliefMethods());
        birthPlan.setBirthPosition(request.getBirthPosition());
        birthPlan.setCordClamping(request.getCordClamping());
        birthPlan.setSkinToSkin(request.getSkinToSkin());
        birthPlan.setBreastfeeding(request.getBreastfeeding());
        birthPlan.setAdditionalNotes(request.getAdditionalNotes());
        birthPlan.setUpdatedAt(LocalDateTime.now());

        // Salvar
        BirthPlanEntity updatedBirthPlan = birthPlanRepository.save(birthPlan);

        // Buscar nome do usuário
        UserEntity user = userRepository.findById(updatedBirthPlan.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return mapToResponse(updatedBirthPlan, user.getName());
    }

    public void deleteBirthPlan(String id) {
        if (!birthPlanRepository.existsById(id)) {
            throw new RuntimeException("Plano de parto não encontrado");
        }
        birthPlanRepository.deleteById(id);
    }

    private BirthPlanResponse mapToResponse(BirthPlanEntity entity, String userName) {
        return BirthPlanResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .userName(userName)
                .companionName(entity.getCompanionName())
                .companionRelationship(entity.getCompanionRelationship())
                .painReliefMethods(entity.getPainReliefMethods())
                .birthPosition(entity.getBirthPosition())
                .cordClamping(entity.getCordClamping())
                .skinToSkin(entity.getSkinToSkin())
                .breastfeeding(entity.getBreastfeeding())
                .additionalNotes(entity.getAdditionalNotes())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
