package cl.ionix.ejercicio.exception;

public class IonixException extends RuntimeException {
    private String errorMessage = "";

    public IonixException(String message) {
        super(message);
    }

    public IonixException(Exception e) {
        super(e);
        errorMessage = e.getMessage();
    }

    public IonixException(Exception e, String msg) {
        super(e);
        errorMessage = msg;
    }

    public String getMensajeError() {
        return errorMessage;
    }

    public void setMensajeError(String msg) {
        errorMessage = msg;
    }
}
