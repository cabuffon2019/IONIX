package cl.ionix.ejercicio.services.impl;

import cl.ionix.ejercicio.client.impl.GetUsuariosClientImpl;
import cl.ionix.ejercicio.dto.Result;
import cl.ionix.ejercicio.dto.UsuarioResponseResource;
import cl.ionix.ejercicio.model.Usuarios;
import cl.ionix.ejercicio.services.UsuarioService;
import cl.ionix.ejercicio.utils.ApplicationConstants;
import cl.ionix.ejercicio.utils.EncryptDecrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.security.GeneralSecurityException;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final GetUsuariosClientImpl getUsuariosClient;


    /**
     * Method to obtain users
     * filtered by rut.
     *
     * @param rut
     * @return usuarioResponseResource
     */
    @Override
    public UsuarioResponseResource usuarioResponseResource(String rut)  {

        UsuarioResponseResource usuarioResponseResource = new UsuarioResponseResource();
        Result result = new Result();
        try {

            String clave = ApplicationConstants.KEY;
            String encryptedRut = EncryptDecrypt.encrypt(clave, rut.trim());

            Usuarios respUsuario = getUsuariosClient.getUsuariosResponse(encryptedRut);
            result.setRegisterCount(respUsuario.getResult().getItems().size());
            usuarioResponseResource.setResponseCode("0");
            usuarioResponseResource.setDescription("OK");
            usuarioResponseResource.setElapsedTime("149");
            usuarioResponseResource.setResult(result);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("No call getUsuario for rut {}", rut);
                usuarioResponseResource.setResponseCode(ApplicationConstants.ERROR_USUARIO_CODE);
                usuarioResponseResource.setDescription(ApplicationConstants.ERROR_USUARIO_MESSAGE);
            } else {
                throw e;
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            usuarioResponseResource.setResponseCode(ApplicationConstants.ERROR_CLAVE_CODE);
            usuarioResponseResource.setDescription(ApplicationConstants.ERROR_CLAVE_MESSAGE);
        }

        return usuarioResponseResource;
    }
}