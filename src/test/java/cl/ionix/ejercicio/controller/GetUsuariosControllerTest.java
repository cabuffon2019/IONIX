package cl.ionix.ejercicio.controller;

import cl.ionix.ejercicio.dto.Result;
import cl.ionix.ejercicio.dto.UsuarioResponseResource;
import cl.ionix.ejercicio.services.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.security.GeneralSecurityException;

@RunWith(MockitoJUnitRunner.class)
public class GetUsuariosControllerTest {

    private static String responseCode = "0";
    private static String description = "OK";
    private static String elapsedTime = "265";
    private static int registerCount = 3;
    private static String rut = "1-9";


    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private GetUsuariosController getUsuariosController;
    @Mock
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getUsuariosController = new GetUsuariosController(usuarioService);
        mockMvc = MockMvcBuilders.standaloneSetup(getUsuariosController).setControllerAdvice(new GetUsuariosControllerAdvice()).build();
    }

    @Test
    public void testGetUsuarios_NotNull() throws GeneralSecurityException {
        when(usuarioService.usuarioResponseResource(anyString())).thenReturn(getUsuarioResponseResource());
        UsuarioResponseResource responseDTO = getUsuariosController.getUsuarios(rut);
        assertNotNull(responseDTO);
    }

    @Test
    public void testGetUsuarios_200_OK() throws Exception {
        when(usuarioService.usuarioResponseResource(anyString())).thenReturn(getUsuarioResponseResource());
        this.mockMvc.perform(get(GetUsuariosController.API_USUARIOS).with(request -> {
            request.setParameter("rut", rut);
            return request;
        })).andExpect(status().isOk());
    }

    @Test
    public void testGetUsuarios_ClientException() throws Exception {
        when(usuarioService.usuarioResponseResource(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        this.mockMvc.perform(get(GetUsuariosController.API_USUARIOS).with(request -> {
            request.setParameter("rut", rut);
            return request;
        })).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetUsuarios_ServerException() throws Exception {
        when(usuarioService.usuarioResponseResource(anyString())).thenThrow(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
        this.mockMvc.perform(get(GetUsuariosController.API_USUARIOS).with(request -> {
            request.setParameter("rut", rut);
            return request;
        })).andExpect(status().isBadGateway());
    }

    private UsuarioResponseResource getUsuarioResponseResource() {
        UsuarioResponseResource usuarioResponseResource = new UsuarioResponseResource();
        Result result = new Result();
        result.setRegisterCount(registerCount);

        usuarioResponseResource.setResponseCode(responseCode);
        usuarioResponseResource.setDescription(description);
        usuarioResponseResource.setElapsedTime(elapsedTime);
        usuarioResponseResource.setResult(result);

        return usuarioResponseResource;
    }
}