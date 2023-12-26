package academy.wakanda.wakacop.sessaovotacao.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/sessao")
public interface SessaoVotacaoAPI {
    @PostMapping(path = "/abertura")
    @ResponseStatus(code = HttpStatus.CREATED)
    SessaoAberturaResponse abreSessao(@RequestBody SessaoAberturaRequest sessaoAberturaRequest);

    @PostMapping(path = "/{idSessao}/voto")
    @ResponseStatus(code = HttpStatus.CREATED)
    VotoResponse recebeVoto(@PathVariable UUID idSessao, @RequestBody VotoRequest novoVoto);

    @GetMapping(path = "/{idSessao}/resultado")
    @ResponseStatus(code = HttpStatus.OK)
    ResultadoSessaoResponse obtemResultado(@PathVariable UUID idSessao);


}
