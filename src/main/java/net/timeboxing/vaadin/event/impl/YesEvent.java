package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class YesEvent extends AbstractComponentEvent {
    public YesEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public YesEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
