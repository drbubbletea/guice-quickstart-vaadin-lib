package net.timeboxing.guice.vaadin.guice.component;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.timeboxing.guice.vaadin.guice.component.impl.DefaultUser;
import net.timeboxing.guice.vaadin.guice.component.impl.User;
import net.timeboxing.guice.vaadin.guice.component.impl.UserEditComponent;
import net.timeboxing.guice.vaadin.guice.component.impl.UserViewComponent;
import net.timeboxing.vaadin.component.ComponentAdapter;
import net.timeboxing.vaadin.component.ComponentPurpose;
import net.timeboxing.vaadin.component.VaadinComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GuiceComponentAdapterRelationshipTest {

    @Test
    void parentAndChild() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        VaadinComponent parent = ComponentAdapter.adapt(user, ComponentPurpose.VIEW).orElseThrow();
        Assertions.assertEquals(UserViewComponent.class, parent.getClass());

        VaadinComponent child = ComponentAdapter.adapt(parent, user, ComponentPurpose.EDIT).orElseThrow();
        Assertions.assertEquals(UserEditComponent.class, child.getClass());

        Assertions.assertTrue(child.isParent(parent));
        Assertions.assertTrue(parent.hasDirectChild());
        Assertions.assertTrue(parent.isDirectChild(child));
        Assertions.assertTrue(parent.isDescendant(child));

        child = null;
        System.gc();
        // no longer considered a child once GCed
        Assertions.assertFalse(parent.hasDirectChild());
    }

    @Test
    void parentAndChildAndDescendant() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        VaadinComponent parent = ComponentAdapter.adapt(user, ComponentPurpose.VIEW).orElseThrow();
        Assertions.assertEquals(UserViewComponent.class, parent.getClass());

        VaadinComponent child = ComponentAdapter.adapt(parent, user, ComponentPurpose.EDIT).orElseThrow();
        Assertions.assertEquals(UserEditComponent.class, child.getClass());

        VaadinComponent descendant = ComponentAdapter.adapt(child, user, ComponentPurpose.EDIT).orElseThrow();
        Assertions.assertEquals(UserEditComponent.class, descendant.getClass());

        Assertions.assertTrue(descendant.isParent(child));
        Assertions.assertTrue(child.isDirectChild(descendant));
        Assertions.assertTrue(child.hasDirectChild());
        Assertions.assertTrue(parent.isDescendant(descendant));

        descendant = null;
        System.gc();
        // no longer considered a child once GCed
        Assertions.assertFalse(child.hasDirectChild());
    }

    @Test
    void parentAndChildExplicitlyRemoveChild() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        VaadinComponent parent = ComponentAdapter.adapt(user, ComponentPurpose.VIEW).orElseThrow();
        Assertions.assertEquals(UserViewComponent.class, parent.getClass());

        VaadinComponent child = ComponentAdapter.adapt(parent, user, ComponentPurpose.EDIT).orElseThrow();
        Assertions.assertEquals(UserEditComponent.class, child.getClass());

        Assertions.assertTrue(child.isParent(parent));
        Assertions.assertTrue(parent.hasDirectChild());
        Assertions.assertTrue(parent.isDirectChild(child));
        Assertions.assertTrue(parent.isDescendant(child));

        parent.disassociateChild(child);

        Assertions.assertFalse(parent.hasDirectChild());
    }

    @Test
    void disassociateRecursively() {
        User user = new DefaultUser(5);
        Injector injector = Guice.createInjector(new TestVaadinComponentModule());
        VaadinComponent parent = ComponentAdapter.adapt(user, ComponentPurpose.VIEW).orElseThrow();
        Assertions.assertEquals(UserViewComponent.class, parent.getClass());

        VaadinComponent child = ComponentAdapter.adapt(parent, user, ComponentPurpose.EDIT).orElseThrow();
        Assertions.assertEquals(UserEditComponent.class, child.getClass());

        VaadinComponent descendant = ComponentAdapter.adapt(child, user, ComponentPurpose.EDIT).orElseThrow();
        Assertions.assertEquals(UserEditComponent.class, descendant.getClass());

        Assertions.assertTrue(parent.hasDirectChild());
        Assertions.assertTrue(child.hasDirectChild());

        parent.disassociate();
        // TODO: validate listener counts become 0
        Assertions.assertFalse(parent.hasDirectChild());
        Assertions.assertFalse(child.hasDirectChild());
    }
}
