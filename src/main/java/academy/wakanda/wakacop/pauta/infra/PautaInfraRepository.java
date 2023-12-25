package academy.wakanda.wakacop.pauta.infra;

import academy.wakanda.wakacop.handler.APIException;
import academy.wakanda.wakacop.pauta.application.repository.PautaRepository;
import academy.wakanda.wakacop.pauta.domain.Pauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PautaInfraRepository implements PautaRepository {
    private final PautaSpringDataJpaRepository pautaSpringDataJpaRepository;

    @Override
    public Pauta salvaPauta(Pauta pauta) {
        log.info("[inicia] PautaInfraRepository - cadastraPauta");
        pautaSpringDataJpaRepository.save(pauta);
        log.info("[finaliza] PautaInfraRepository - cadastraPauta");
        return pauta;
    }

    @Override
    public Pauta buscarPautaPorId(UUID idPauta) {
        log.info("[inicia] PautaInfraRepository - buscarPautaPorId");
        Pauta pautaPorId = pautaSpringDataJpaRepository.findById(idPauta)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Pauta não encontrada!"));
        log.info("[finaliza] PautaInfraRepository - buscarPautaPorId");
        return pautaPorId;
    }
}
