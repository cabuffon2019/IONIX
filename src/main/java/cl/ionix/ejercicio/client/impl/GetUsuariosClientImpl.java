package cl.ionix.ejercicio.client.impl;

import cl.ionix.ejercicio.client.GetUsuariosClient;
import cl.ionix.ejercicio.model.UsuarioRequest;
import cl.ionix.ejercicio.model.Usuarios;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class GetUsuariosClientImpl implements GetUsuariosClient {

    private final RestTemplate restTemplate;

    @Value("${get-usuarios.uri}")
    private String getUsuariosUri;

    @Override
    public Usuarios getUsuariosResponse(String rut) {

        UsuarioRequest usuarioReq = new UsuarioRequest();
        usuarioReq.setRut(rut);


        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(getUsuariosUri)
                .queryParam("rut", rut);

        ResponseEntity<Usuarios> responseResponseEntity = restTemplate
                .exchange(getUsuariosUri + "?rut=" + rut, HttpMethod.GET,
                        new HttpEntity<>(headers), new ParameterizedTypeReference<Usuarios>() {
                        });

        Usuarios usuarios = responseResponseEntity.getBody();

        System.out.println("RESULTADO: "+usuarios);

        return usuarios;

    }
}