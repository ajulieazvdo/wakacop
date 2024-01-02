package academy.wakanda.wakacop.sessaovotacao.application.service;

import academy.wakanda.wakacop.sessaovotacao.application.repository.SessaoVotacaoRepository;
import academy.wakanda.wakacop.sessaovotacao.domain.PublicadorResultadoSessao;
import academy.wakanda.wakacop.sessaovotacao.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoFechadorService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PublicadorResultadoSessao publicador;

    @Scheduled(cron = "0 * * * * *")
    public void fechaSessoesEncerradas(){
        log.debug("[inicia] SessaoVotacaoFechadorService - fechaSessoesEncerradas");
        List<SessaoVotacao> sessoesAbertas = sessaoVotacaoRepository.buscaAbertas();
        log.debug("[sessoesAbertas] {}", sessoesAbertas);
        sessoesAbertas.forEach(sessaoVotacao -> {
            sessaoVotacao.obtemResultado(publicador);
            sessaoVotacaoRepository.salvaSessao(sessaoVotacao);
        });
        log.debug("[finaliza] SessaoVotacaoFechadorService - fechaSessoesEncerradas");
    }
}