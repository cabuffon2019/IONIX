package cl.ionix.ejercicio.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@ControllerAdvice
public class GetUsuariosControllerAdvice {

    /**
     * Method for handling the HttpStatusCodeException
     *
     * @param e
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(value = HttpStatusCodeException.class)
    public void ionixOnlineExceptionHandler(HttpStatusCodeException e, HttpServletResponse response) throws IOException {
        log.error("Status code exception ocurred {} : {} ", e.getMessage(), e.getResponseBodyAsString());
        response.sendError(e.getStatusCode().value(), "Ha Occurido un error en la llamada al servicio . Mensaje : " + e.getResponseBodyAsString());
    }


}
