package cl.ionix.ejercicio.services;

import cl.ionix.ejercicio.dto.UsuarioResponseResource;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public interface UsuarioService {
    UsuarioResponseResource usuarioResponseResource(String rut) throws GeneralSecurityException;
}
