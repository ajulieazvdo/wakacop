package academy.wakanda.wakacop.sessaovotacao.application.api;

import academy.wakanda.wakacop.sessaovotacao.application.service.SessaoVotacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoController implements SessaoVotacaoAPI {
    private final SessaoVotacaoService sessaoVotacaoService;

    @Override
    public SessaoAberturaResponse abreSessao(SessaoAberturaRequest sessaoAberturaRequest) {
        log.info("[inicia] SessaoVotacaoController - abreSessao");
        SessaoAberturaResponse sessaoAbertura = sessaoVotacaoService.abreSessao(sessaoAberturaRequest);
        log.info("[finaliza] SessaoVotacaoController - abreSessao");
        return sessaoAbertura;
    }

    @Override
    public VotoResponse recebeVoto(UUID idSessao, VotoRequest novoVoto) {
        log.debug("[inicia] SessaoVotacaoController - recebeVoto");
        log.debug("[idSessao] {}", idSessao);
        VotoResponse votoResponse = sessaoVotacaoService.recebeVoto(idSessao, novoVoto);
        log.debug("[finaliza] SessaoVotacaoController - recebeVoto");
        return votoResponse;
    }

    @Override
    public ResultadoSessaoResponse obtemResultado(UUID idSessao) {
        log.info("[inicia] SessaoVotacaoController - obtemResultado");
        ResultadoSessaoResponse resultadoCriado = sessaoVotacaoService.obtemResultado(idSessao);
        log.info("[finaliza] SessaoVotacaoController - obtemResultado");
        return resultadoCriado;
    }
}
