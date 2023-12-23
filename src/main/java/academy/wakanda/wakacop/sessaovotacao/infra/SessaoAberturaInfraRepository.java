package academy.wakanda.wakacop.sessaovotacao.infra;

import academy.wakanda.wakacop.sessaovotacao.application.repository.SessaoAberturaRepository;
import academy.wakanda.wakacop.sessaovotacao.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class SessaoAberturaInfraRepository implements SessaoAberturaRepository {
    private final SessaoVotacaoSpringDataJpaRepository sessaoVotacaoSpringDataJpaRepository;

    @Override
    public SessaoVotacao salvaSessao(SessaoVotacao sessaoVotacao) {
        log.info("[inicia] SessaoAberturaInfraRepository - salvaSessao");
        sessaoVotacaoSpringDataJpaRepository.save(sessaoVotacao);
        log.info("[finaliza] SessaoAberturaInfraRepository - salvaSessao");
        return sessaoVotacao;
    }
}
