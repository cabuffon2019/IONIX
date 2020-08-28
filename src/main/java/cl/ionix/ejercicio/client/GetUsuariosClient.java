package cl.ionix.ejercicio.client;

import cl.ionix.ejercicio.model.Usuarios;

public interface GetUsuariosClient {

    Usuarios getUsuariosResponse(String rut);
}

