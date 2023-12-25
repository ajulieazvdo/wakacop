package academy.wakanda.wakacop.pauta.application.service;

import academy.wakanda.wakacop.pauta.application.api.NovaPastaRequest;
import academy.wakanda.wakacop.pauta.application.api.PautaCadastradaReponse;
import academy.wakanda.wakacop.pauta.application.repository.PautaRepository;
import academy.wakanda.wakacop.pauta.domain.Pauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class PautaApplicationService implements PautaService {
    private final PautaRepository pautaRepository;

    @Override
    public PautaCadastradaReponse cadastraPauta(NovaPastaRequest novaPasta) {
        log.info("[inicia] PautaApplicationService - cadastraPauta");
        log.info("[novaPasta] - {}", novaPasta);
        Pauta pauta = pautaRepository.salvaPauta(new Pauta(novaPasta));
        log.info("[finaliza] PautaApplicationService - cadastraPauta");
        return new PautaCadastradaReponse(pauta);
    }

    @Override
    public Pauta getPautaPorId(UUID idPauta) {
        log.info("[inicia] PautaApplicationService - getPautaPorId");
        Pauta pautaPorId = pautaRepository.buscarPautaPorId(idPauta);
        log.info("[finaliza] PautaApplicationService - getPautaPorId");
        return pautaPorId;
    }
}
