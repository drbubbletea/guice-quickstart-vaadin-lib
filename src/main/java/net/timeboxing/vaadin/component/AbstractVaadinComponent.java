package net.timeboxing.vaadin.component;

import net.timeboxing.vaadin.event.ListenerRegistrations;
import net.timeboxing.vaadin.event.impl.DefaultListenerRegistrations;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractVaadinComponent implements VaadinComponent {

    protected final ListenerRegistrations registrations = new DefaultListenerRegistrations();

    /**
     * It is assumed the parent is not something changed frequently enough we need to worry about thread safety for
     * modifications.
     */
    private VaadinComponent parent = null;

    /**
     * May consider using a weak set or weak map instead.
     */
    private AtomicReference<List<WeakReference<VaadinComponent>>> children = new AtomicReference<>(new ArrayList<>());

    @Override
    public final void addParent(VaadinComponent parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(VaadinComponent child) {
        children.getAndUpdate(current -> {
            List<WeakReference<VaadinComponent>> modified = new ArrayList<>(current);
            modified.add(new WeakReference<>(child));
            return modified;
        });
    }

    @Override
    public final boolean isParent(VaadinComponent component) {
        return parent != null && parent.equals(component);
    }

    @Override
    public final boolean isDirectChild(VaadinComponent component) {
        List<WeakReference<VaadinComponent>> directChildren = children.get();
        Iterator<WeakReference<VaadinComponent>> iterator = directChildren.iterator();
        while (iterator.hasNext()) {
            WeakReference<VaadinComponent> ref = iterator.next();
            VaadinComponent referenced = ref.get();
            if (null != referenced) {
                if (component.equals(referenced)) {
                    return true;
                }
            } else {
                iterator.remove();
            }
        }
        return false;
    }

    @Override
    public final boolean isDescendant(VaadinComponent component) {
        if (isDirectChild(component)) {
            return true;
        } else {
            List<WeakReference<VaadinComponent>> directChildren = children.get();
            for (WeakReference<VaadinComponent> ref : directChildren) {
                VaadinComponent referenced = ref.get();
                if (referenced != null && referenced.isDescendant(component)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public final boolean isParentOrDirectChild(VaadinComponent component) {
        return isParent(component) || isDirectChild(component);
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public boolean hasDirectChild() {
        List<WeakReference<VaadinComponent>> directChildren = children.get();
        Iterator<WeakReference<VaadinComponent>> iterator = directChildren.iterator();
        while (iterator.hasNext()) {
            WeakReference<VaadinComponent> ref = iterator.next();
            VaadinComponent referenced = ref.get();
            if (null != referenced) {
                return true;
            } else {
                iterator.remove();
            }
        }
        return false;
    }

    @Override
    public void disassociateChild(VaadinComponent component) {
        List<WeakReference<VaadinComponent>> directChildren = children.get();
        Iterator<WeakReference<VaadinComponent>> iterator = directChildren.iterator();
        while (iterator.hasNext()) {
            WeakReference<VaadinComponent> ref = iterator.next();
            VaadinComponent referenced = ref.get();
            if (null != referenced && referenced.equals(component)) {
                referenced.disassociate();
                iterator.remove();
            } else if (null == referenced) {
                iterator.remove();
            }
        }
    }

    @Override
    public final void disassociate() {
        registrations.removeAll();
        List<WeakReference<VaadinComponent>> directChildren = children.get();
        Iterator<WeakReference<VaadinComponent>> iterator = directChildren.iterator();
        while (iterator.hasNext()) {
            VaadinComponent reference = iterator.next().get();
            if (reference != null) {
                reference.disassociate();
            }
            iterator.remove();
        }
    }
}
