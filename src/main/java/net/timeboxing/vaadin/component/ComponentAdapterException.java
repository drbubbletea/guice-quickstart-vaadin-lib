package net.timeboxing.vaadin.component;

public class ComponentAdapterException extends RuntimeException {

    public ComponentAdapterException() {
    }

    public ComponentAdapterException(String message) {
        super(message);
    }

    public ComponentAdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentAdapterException(Throwable cause) {
        super(cause);
    }

    public ComponentAdapterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
