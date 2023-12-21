package academy.wakanda.wakacop.pauta.application.service;

import academy.wakanda.wakacop.pauta.application.api.NovaPastaRequest;
import academy.wakanda.wakacop.pauta.application.api.PautaCadastradaReponse;

public interface PautaService {
    PautaCadastradaReponse cadastraPauta(NovaPastaRequest novaPasta);
}
