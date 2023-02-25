package net.timeboxing.vaadin.event;

import net.timeboxing.listener.ListenerRegistration;

public interface ListenerRegistrations {

    void add(ListenerRegistration registration);

    void removeAll();
}
