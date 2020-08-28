package cl.ionix.ejercicio.controller;

import cl.ionix.ejercicio.dto.UsuarioResponseResource;
import cl.ionix.ejercicio.services.UsuarioService;
import cl.ionix.ejercicio.utils.ApplicationConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.GeneralSecurityException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GetUsuariosController {

    private final UsuarioService usuarioService;
    UsuarioResponseResource usuarioResponseResource = new UsuarioResponseResource();

    static final String API_USUARIOS = "/api/usuarios";

    /**
     * Method for getting the shift usuarios available response
     *
     * @param {rut}
     * @return response
     */
    @GetMapping(value = API_USUARIOS)
    public UsuarioResponseResource getUsuarios(
            @Valid @RequestParam(value = "rut") String rut) {
        log.info("Request Received: {}", rut);
        try {
            return usuarioService.usuarioResponseResource(rut);
        } catch (GeneralSecurityException e) {
            usuarioResponseResource.setResponseCode(ApplicationConstants.ERROR_CLAVE_CODE);
            usuarioResponseResource.setDescription(ApplicationConstants.ERROR_CLAVE_MESSAGE);
            return usuarioResponseResource;
        }
    }
}