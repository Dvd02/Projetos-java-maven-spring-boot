
package br.com.david.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionPosseNaoEncontrada extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public ExceptionPosseNaoEncontrada(String message) {
        super(message);
    }

}
