package academy.wakanda.wakacop.pauta.domain;

import academy.wakanda.wakacop.pauta.application.api.NovaPastaRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private UUID id;
    private String titulo;
    private String descricao;
    private UUID idAssociadoAutor;
    private LocalDateTime dataCricao;

    public Pauta(NovaPastaRequest novaPasta) {
        this.titulo = novaPasta.getTitulo();
        this.descricao = novaPasta.getDescricao();
        this.idAssociadoAutor = novaPasta.getIdAssociadoAutor();
        this.dataCricao = LocalDateTime.now();
    }
}
