package academy.wakanda.wakacop.sessaovotacao.application.repository;

import academy.wakanda.wakacop.sessaovotacao.domain.SessaoVotacao;

public interface SessaoAberturaRepository {
    SessaoVotacao salvaSessao(SessaoVotacao sessaoVotacao);
}
