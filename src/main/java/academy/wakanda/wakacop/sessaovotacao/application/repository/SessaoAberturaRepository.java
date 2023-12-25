package academy.wakanda.wakacop.sessaovotacao.application.repository;

import academy.wakanda.wakacop.sessaovotacao.domain.SessaoVotacao;

import java.util.UUID;

public interface SessaoAberturaRepository {
    SessaoVotacao salvaSessao(SessaoVotacao sessaoVotacao);
    SessaoVotacao buscaPorId(UUID idSessao);
}
