package academy.wakanda.wakacop.sessaovotacao.infra;

import academy.wakanda.wakacop.handler.APIException;
import academy.wakanda.wakacop.sessaovotacao.application.repository.SessaoAberturaRepository;
import academy.wakanda.wakacop.sessaovotacao.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;

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

    @Override
    public SessaoVotacao buscaPorId(UUID idSessao) {
        log.info("[inicia] SessaoAberturaInfraRepository - buscaPorId");
        SessaoVotacao sessao = sessaoVotacaoSpringDataJpaRepository.findById(idSessao)
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
        log.info("[finaliza] SessaoAberturaInfraRepository - buscaPorId");
        return sessao;
    }
}
