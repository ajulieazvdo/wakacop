package academy.wakanda.wakacop.associado.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AssociadoApplicationService implements AssociadoService {

    @Override
    public void validaAssociadoAptoVoto(String cpfAssociado) {
        log.debug("[inicia] AssociadoApplicationService - validaAssociadoAptoVoto");
        
        log.debug("[finaliza] AssociadoApplicationService - validaAssociadoAptoVoto");
    }
}
