package humanize.service.seeders;

import humanize.service.entities.ContentEntity;
import humanize.service.repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentSeeder implements CommandLineRunner {

    private final ContentRepository contentRepository;

    @Override
    public void run(String... args) throws Exception {

        if (contentRepository.count() > 0) {
            System.out.println("Conteúdos já existem — Seeder ignorado.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        List<ContentEntity> contents = Arrays.asList(

                // ===============================
                // GESTANTE
                // ===============================
                ContentEntity.builder()
                        .title("Primeiros sinais de evolução da gestação")
                        .text("<p>No início da gravidez, é comum sentir mudanças como sensibilidade nos seios, leve cansaço e maior sensibilidade emocional. Essas transformações fazem parte do processo natural de adaptação do corpo.</p>")
                        .role("gestante")
                        .type("informativo")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                ContentEntity.builder()
                        .title("Dica de autocuidado para gestantes")
                        .text("<p>Separe alguns minutos do seu dia para respirar profundamente e alongar o corpo. Pequenos momentos de pausa ajudam a reduzir a ansiedade e melhorar seu bem-estar.</p>")
                        .role("gestante")
                        .type("dica")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                // ===============================
                // ACOMPANHANTE
                // ===============================
                ContentEntity.builder()
                        .title("Como apoiar a gestante no dia a dia")
                        .text("<p>Ajude com pequenas tarefas, incentive o descanso e esteja disponível para ouvir. A gestação é emocionalmente intensa, e apoio contínuo faz diferença.</p>")
                        .role("acompanhante")
                        .type("informativo")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                ContentEntity.builder()
                        .title("Dica rápida para acompanhantes")
                        .text("<p>Pergunte diariamente como a gestante está se sentindo. O simples gesto de perguntar já demonstra cuidado e atenção.</p>")
                        .role("acompanhante")
                        .type("dica")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                // ===============================
                // TENTANTE
                // ===============================
                ContentEntity.builder()
                        .title("Entendendo o ciclo menstrual")
                        .text("<p>Conhecer seu ciclo ajuda a identificar o período fértil. Mesmo ciclos irregulares têm padrões que podem ser observados.</p>")
                        .role("tentante")
                        .type("informativo")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                ContentEntity.builder()
                        .title("Dica para tentantes")
                        .text("<p>Manter um diário simples dos ciclos pode facilitar a percepção de sinais como temperatura basal e muco cervical.</p>")
                        .role("tentante")
                        .type("dica")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                // ===============================
                // PROFISSIONAL
                // ===============================
                ContentEntity.builder()
                        .title("Atualização rápida sobre humanização do parto")
                        .text("<p>Abordagens centradas na mulher incluem autonomia nas decisões, ambiente acolhedor e comunicação clara entre equipe e família.</p>")
                        .role("profissional")
                        .type("informativo")
                        .createdAt(now)
                        .updatedAt(now)
                        .build(),

                ContentEntity.builder()
                        .title("Prática recomendada no pré-natal")
                        .text("<p>Estimule discussões antecipadas sobre o Plano de Parto. Isso fortalece o vínculo e melhora a experiência materna.</p>")
                        .role("profissional")
                        .type("dica")
                        .createdAt(now)
                        .updatedAt(now)
                        .build()
        );

        contentRepository.saveAll(contents);
        System.out.println("Seeder de conteúdo executado com sucesso!");
    }
}
