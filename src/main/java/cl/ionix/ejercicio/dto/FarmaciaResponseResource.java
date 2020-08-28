package cl.ionix.ejercicio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmaciaResponseResource {

    private String errorCode;
    private String errorDesc;
    private String nombreLocal;
    private String direccion;
    private String telefono;
    private String latitud;
    private String longitud;

}
