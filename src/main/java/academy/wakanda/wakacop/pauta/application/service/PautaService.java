package academy.wakanda.wakacop.pauta.application.service;

import academy.wakanda.wakacop.pauta.application.api.NovaPastaRequest;
import academy.wakanda.wakacop.pauta.application.api.PautaCadastradaReponse;
import academy.wakanda.wakacop.pauta.domain.Pauta;

import java.util.UUID;

public interface PautaService {
    PautaCadastradaReponse cadastraPauta(NovaPastaRequest novaPasta);
    Pauta getPautaPorId(UUID idPauta);
}
