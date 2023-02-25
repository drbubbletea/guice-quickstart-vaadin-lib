package net.timeboxing.vaadin.event;

import net.timeboxing.vaadin.component.VaadinComponent;

import java.util.Optional;

public interface ComponentEvent {

    /**
     * The VaadinComponent that produced this event.
     * @return
     */
    VaadinComponent producedBy();

    /**
     * The data associated with this event.
     */
    <T> Optional<T> data(Class<T> dataClass);
}
