package academy.wakanda.wakacop.pauta.application.api;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@Getter
public class NovaPastaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private UUID idAssociadoAutor;
}
