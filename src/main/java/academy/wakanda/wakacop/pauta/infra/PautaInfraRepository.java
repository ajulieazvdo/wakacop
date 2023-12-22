package academy.wakanda.wakacop.pauta.infra;

import academy.wakanda.wakacop.pauta.application.repository.PautaRepository;
import academy.wakanda.wakacop.pauta.domain.Pauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

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
}
