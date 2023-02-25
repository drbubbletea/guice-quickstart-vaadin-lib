package net.timeboxing.vaadin.event.impl;

import net.timeboxing.listener.ListenerRegistration;
import net.timeboxing.vaadin.event.ListenerRegistrations;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultListenerRegistrations implements ListenerRegistrations {

    // TODO: use weak map or find a weak collection implementation
    private List<WeakReference<ListenerRegistration>> registrations = new ArrayList<>();

    @Override
    public void add(ListenerRegistration registration) {
        registrations.add(new WeakReference<>(registration));
    }

    @Override
    public void removeAll() {
        Iterator<WeakReference<ListenerRegistration>> iterator = registrations.iterator();
        while (iterator.hasNext()) {
            ListenerRegistration registration = iterator.next().get();
            if (registration != null) {
                registration.unregister();
            }
            iterator.remove();
        }
    }
}
