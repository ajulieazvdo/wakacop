package academy.wakanda.wakacop.sessaovotacao.domain;

import academy.wakanda.wakacop.associado.application.service.AssociadoService;
import academy.wakanda.wakacop.pauta.domain.Pauta;
import academy.wakanda.wakacop.sessaovotacao.application.api.ResultadoSessaoResponse;
import academy.wakanda.wakacop.sessaovotacao.application.api.SessaoAberturaRequest;
import academy.wakanda.wakacop.sessaovotacao.application.api.VotoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

class SessaoVotacaoTest {

    @Mock
    private AssociadoService associadoService;

    @Mock
    private PublicadorResultadoSessao publicadorResultadoSessao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSessaoAbertura() {
        Pauta pauta = Pauta.builder().build();
        SessaoAberturaRequest aberturaRequest = SessaoAberturaRequest.builder()
                .idPauta(UUID.randomUUID())
                .build();

        SessaoVotacao sessaoVotacao = new SessaoVotacao(aberturaRequest, pauta);

        assertEquals(pauta.getId(), sessaoVotacao.getIdPauta());
        assertEquals(1, sessaoVotacao.getTempoDuracao());
        assertNotNull(sessaoVotacao.getMomentoAbertura());
        assertNotNull(sessaoVotacao.getMomentoEncerramento());
        assertEquals(StatusSessaoVotacao.ABERTO, sessaoVotacao.getStatus());
        assertNotNull(sessaoVotacao.getVotos());
        assertTrue(sessaoVotacao.getVotos().isEmpty());
    }

    @Test
    void testRecebeVoto() {
        Pauta pauta = Pauta.builder().build();
        SessaoAberturaRequest aberturaRequest = SessaoAberturaRequest.builder()
                .idPauta(UUID.randomUUID())
                .build();

        SessaoVotacao sessaoVotacao = new SessaoVotacao(aberturaRequest, pauta);

        VotoRequest votoRequest = VotoRequest.builder()
                .cpfAssociado("123456789")
                .opcao(OpcaoVoto.SIM)
                .build();

        VotoPauta votoPauta = sessaoVotacao.recebeVoto(votoRequest, associadoService, publicadorResultadoSessao);

        assertNotNull(votoPauta);
        assertEquals(sessaoVotacao, votoPauta.getSessaoVotacao());
        assertEquals(votoRequest.getCpfAssociado(), votoPauta.getCpfAssociado());
        assertEquals(votoRequest.getOpcao(), votoPauta.getOpcaoVoto());
        assertNotNull(votoPauta.getMomentoVoto());
        assertTrue(sessaoVotacao.getVotos().containsKey(votoRequest.getCpfAssociado()));
    }

    @Test
    void testValidaAssociado() {
        Pauta pauta = Pauta.builder().build();
        SessaoAberturaRequest aberturaRequest = SessaoAberturaRequest.builder()
                .idPauta(UUID.randomUUID())
                .build();

        SessaoVotacao sessaoVotacao = new SessaoVotacao(aberturaRequest, pauta);

        VotoRequest votoRequest = VotoRequest.builder()
                .cpfAssociado("123456789")
                .build();

        doNothing().when(associadoService).validaAssociadoAptoVoto(votoRequest.getCpfAssociado());

        sessaoVotacao.validaAssociado(votoRequest.getCpfAssociado(), associadoService);
    }

    @Test
    void testValidaVotoDuplicado() {
        Pauta pauta = Pauta.builder().build();
        SessaoAberturaRequest aberturaRequest = SessaoAberturaRequest.builder()
                .idPauta(UUID.randomUUID())
                .build();

        SessaoVotacao sessaoVotacao = new SessaoVotacao(aberturaRequest, pauta);

        VotoRequest votoRequest = VotoRequest.builder()
                .cpfAssociado("123456789")
                .build();

        sessaoVotacao.recebeVoto(votoRequest, associadoService, publicadorResultadoSessao);

        assertThrows(RuntimeException.class, () -> sessaoVotacao.validaVotoDuplicado(votoRequest.getCpfAssociado()));
    }

    @Test
    void testObtemResultado() {
        Pauta pauta = Pauta.builder().build();
        SessaoAberturaRequest aberturaRequest = SessaoAberturaRequest.builder()
                .idPauta(UUID.randomUUID())
                .build();

        SessaoVotacao sessaoVotacao = new SessaoVotacao(aberturaRequest, pauta);

        ResultadoSessaoResponse resultadoSessaoResponse = sessaoVotacao.obtemResultado(publicadorResultadoSessao);

        assertNotNull(resultadoSessaoResponse);
        assertEquals(sessaoVotacao.getId(), resultadoSessaoResponse.getIdSessao());
        assertEquals(sessaoVotacao.getIdPauta(), resultadoSessaoResponse.getIdPauta());
        assertEquals(sessaoVotacao.getStatus(), resultadoSessaoResponse.getStatus());
        assertEquals(sessaoVotacao.getMomentoAbertura(), resultadoSessaoResponse.getMomentoAbertura());
        assertEquals(sessaoVotacao.getMomentoEncerramento(), resultadoSessaoResponse.getMomentoEncerramento());
        assertEquals(sessaoVotacao.getTotalVotos(), resultadoSessaoResponse.getTotalVotos());
        assertEquals(sessaoVotacao.getTotalSim(), resultadoSessaoResponse.getTotalSim());
        assertEquals(sessaoVotacao.getTotalNao(), resultadoSessaoResponse.getTotalNao());
    }

    @Test
    void deveFecharSessaoQuandoChamarMetodoFechaSessao(){
        SessaoVotacao sessao = buildSessao();

        PublicadorResultadoSessao publicador = new PublicadorResultadoSessaoMockTest();
        sessao.fechaSessao(publicador);

        assertEquals(StatusSessaoVotacao.FECHADA, sessao.getStatus());
    }

    @Test
    void deveFecharSessaoQuandoStatusAbertaEMomentoEncerramentoEstiverNoPassado(){
        SessaoVotacao sessao = buildSessao();

        PublicadorResultadoSessao publicador = new PublicadorResultadoSessaoMockTest();
        sessao.atualizaStatus(publicador);

        assertEquals(StatusSessaoVotacao.FECHADA, sessao.getStatus());
    }

    @Test
    void deveFicarAbertaSessaoQuandoStatusAbertaEMomentoEncerramentoEstiverNoFuturo(){
        SessaoVotacao sessao = buildSessaoEncerramentoFuturo();

        PublicadorResultadoSessao publicador = new PublicadorResultadoSessaoMockTest();
        sessao.atualizaStatus(publicador);

        assertEquals(StatusSessaoVotacao.ABERTO, sessao.getStatus());
    }

    private SessaoVotacao buildSessaoEncerramentoFuturo() {
        return SessaoVotacao.builder()
                .id(UUID.randomUUID())
                .idPauta(UUID.randomUUID())
                .status(StatusSessaoVotacao.ABERTO)
                .momentoAbertura(LocalDateTime.of(2023,1,1,1,1))
                .momentoEncerramento(LocalDateTime.MAX)
                .votos(getVotos())
                .build();
    }

    private SessaoVotacao buildSessao() {
        return SessaoVotacao.builder()
                .id(UUID.randomUUID())
                .idPauta(UUID.randomUUID())
                .status(StatusSessaoVotacao.ABERTO)
                .momentoAbertura(LocalDateTime.of(2023,1,1,1,1))
                .momentoEncerramento(LocalDateTime.of(2023,1,1,1,2))
                .votos(getVotos())
                .build();
    }

    private Map<String, VotoPauta> getVotos() {
        return Map.of("04389272156", VotoPauta.builder()
                        .cpfAssociado("04389272156")
                        .opcaoVoto(OpcaoVoto.SIM).build(),
                "04389272155", VotoPauta.builder()
                .cpfAssociado("04389272155")
                .opcaoVoto(OpcaoVoto.NAO).build());
    }
}