package cl.ionix.ejercicio.exception;

public class ConsorcioPharmacyException extends RuntimeException {
    private String errorMessage = "";

    public ConsorcioPharmacyException(String message) {
        super(message);
    }

    public ConsorcioPharmacyException(Exception e) {
        super(e);
        errorMessage = e.getMessage();
    }

    public ConsorcioPharmacyException(Exception e, String msg) {
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
