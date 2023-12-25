package academy.wakanda.wakacop.pauta.application.repository;

import academy.wakanda.wakacop.pauta.domain.Pauta;

import java.util.UUID;

public interface PautaRepository {
    Pauta salvaPauta(Pauta pauta);
    Pauta buscarPautaPorId(UUID idPauta);
}
