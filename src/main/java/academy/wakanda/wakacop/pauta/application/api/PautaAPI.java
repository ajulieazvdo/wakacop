package academy.wakanda.wakacop.pauta.application.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pauta")
public interface PautaAPI {
    PautaCadastradaReponse cadastraPauta(NovaPastaRequest novaPasta);
}
