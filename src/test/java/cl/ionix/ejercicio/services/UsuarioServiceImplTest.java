package cl.ionix.ejercicio.services;

import cl.ionix.ejercicio.client.impl.GetUsuariosClientImpl;
import cl.ionix.ejercicio.dto.UsuarioResponseResource;
import cl.ionix.ejercicio.model.Usuarios;
import cl.ionix.ejercicio.services.impl.UsuarioServiceImpl;
import cl.ionix.ejercicio.utils.EncryptDecrypt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    private static String responseCode = "0";
    private static String description = "OK";
    private static String elapsedTime = "265";
    private static int registerCount = 3;
    private static String rut = "1-9";

    @Mock
    private EncryptDecrypt encryptDecrypt;

    @Mock
    private GetUsuariosClientImpl getUsuariosClientImpl;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Before
    public void init() throws IOException {
        when(getUsuariosClientImpl.getUsuariosResponse(anyString())).thenReturn(getUsuarios());
    }


    @Test
    public void usuarioResponseResourceTest() {
        UsuarioResponseResource usuarioResponseResource;
        when(getUsuariosClientImpl.getUsuariosResponse(anyString())).thenReturn(getUsuarios());
        usuarioResponseResource = usuarioServiceImpl.usuarioResponseResource(rut);
        assertNotNull(usuarioResponseResource);

    }

    @Test
    public void usuarioResponseResourceNotFoundException() {
        UsuarioResponseResource usuarioResponseResource;
        when(getUsuariosClientImpl.getUsuariosResponse(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        usuarioResponseResource = usuarioServiceImpl.usuarioResponseResource(rut);
        assertNotNull(usuarioResponseResource);
    }

    @Test(expected = HttpClientErrorException.class)
    public void usuarioResponseResourceClientException() {
        UsuarioResponseResource usuarioResponseResource;
        when(getUsuariosClientImpl.getUsuariosResponse(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        usuarioServiceImpl.usuarioResponseResource(rut);
    }



    private UsuarioResponseResource getUsuarioResponseResource() {
        UsuarioResponseResource messageHomeResponseDTo = UsuarioResponseResource.builder().
                responseCode(responseCode).description(description).build();

        return messageHomeResponseDTo;
    }

    private Usuarios getUsuarios() {
        Usuarios usuarioResponseModel = Usuarios.builder().responseCode(responseCode).description(description).build();
        return usuarioResponseModel;
    }
}
