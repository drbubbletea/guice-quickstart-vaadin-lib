package net.timeboxing.vaadin.event;

import net.timeboxing.listener.ListenerRegistration;

public interface VaadinComponentEventBus {

    /**
     * Listen for events of a specified class. If applicable, listeners are responsible for ensuring the source of the
     * event is a producer the listener is interested in receiving such events from.
     */
    <T extends ComponentEvent> ListenerRegistration listen(Class<T> event, VaadinComponentEventListener<T> listener);

    <T extends ComponentEvent> void send (T event);
}
