package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class UpdateEvent extends AbstractComponentEvent {
    public UpdateEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public UpdateEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
