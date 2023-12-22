package academy.wakanda.wakacop.pauta.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pauta")
public interface PautaAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PautaCadastradaReponse cadastraPauta(@RequestBody @Valid NovaPastaRequest novaPasta);
}
