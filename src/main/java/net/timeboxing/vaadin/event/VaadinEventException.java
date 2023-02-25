package net.timeboxing.vaadin.event;

public class VaadinEventException extends RuntimeException {

    public VaadinEventException() {
        super();
    }

    public VaadinEventException(String message) {
        super(message);
    }

    public VaadinEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public VaadinEventException(Throwable cause) {
        super(cause);
    }
}
