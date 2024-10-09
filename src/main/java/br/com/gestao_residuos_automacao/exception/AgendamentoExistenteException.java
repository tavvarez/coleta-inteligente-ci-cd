package br.com.gestao_residuos_automacao.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AgendamentoExistenteException extends RuntimeException {

    public AgendamentoExistenteException() {
        super();
    }

    public AgendamentoExistenteException(String message) {
        super(message);
    }

    public AgendamentoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgendamentoExistenteException(Throwable cause) {
        super(cause);
    }
}
