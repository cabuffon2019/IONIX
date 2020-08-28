package cl.ionix.ejercicio.utils;


public class ApplicationConstants {

    public static final String ERROR_USUARIO_CODE = "100001";
    public static final String ERROR_USUARIO_MESSAGE = "Error Ocurrido al llamar al servicio getUsuarios";
    public static final String ERROR_CLAVE_CODE = "100002";
    public static final String ERROR_CLAVE_MESSAGE = "Error Ocurrido al encriptar la clave";
    public static final String KEY = "ionix123456";

    private ApplicationConstants() {
        throw new IllegalStateException("Utility class");
    }


}
