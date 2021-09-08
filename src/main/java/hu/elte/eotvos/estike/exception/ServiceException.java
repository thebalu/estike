package hu.elte.eotvos.estike.exception;

public abstract class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected ServiceException(String message) {
        super(message);
    }

    protected ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
