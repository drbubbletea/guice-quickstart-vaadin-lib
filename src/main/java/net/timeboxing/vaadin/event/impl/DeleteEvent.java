package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class DeleteEvent extends AbstractComponentEvent {
    public DeleteEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public DeleteEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
