package net.timeboxing.vaadin.event;

@FunctionalInterface
public interface VaadinComponentEventListener<T extends ComponentEvent> {

    void onEvent(T event);
}
