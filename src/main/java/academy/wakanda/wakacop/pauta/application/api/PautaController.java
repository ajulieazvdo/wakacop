package academy.wakanda.wakacop.pauta.application.api;

import academy.wakanda.wakacop.pauta.application.service.PautaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PautaController implements PautaAPI {
    private final PautaService pautaService;

    @Override
    public PautaCadastradaReponse cadastraPauta(NovaPastaRequest novaPasta) {
        log.info("[inicia] PautaController - cadastraPauta");
        PautaCadastradaReponse pautaCadastrada = pautaService.cadastraPauta(novaPasta);
        log.info("[finaliza] PautaController - cadastraPauta");
        return pautaCadastrada;
    }
}
