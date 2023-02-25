package net.timeboxing.vaadin.component;

import com.vaadin.flow.component.Component;


public interface VaadinComponent {

    /**
     * Get the Vaadin component class associated with this object.
     * @return
     */
    Component get();

    /**
     * Set the parent of this class.
     */
    void addParent(VaadinComponent parent);

    /**
     * Add a child of this class.
     */
    void addChild(VaadinComponent child);

    /**
     *
     * @param component component in question
     * @return true if the specified component is the parent of this class. false if not or no parent is defined.
     */
    boolean isParent(VaadinComponent component);

    /**
     *
     * @return true if this class has a parent. false otherwise
     */
    boolean hasParent();

    /**
     *
     * @param component component in question
     * @return true if the specified component is a direct child of this class. false if not or no children are defined.
     */
    boolean isDirectChild(VaadinComponent component);

    /**
     *
     * @return true if this class has a child. false otherwise.
     */
    boolean hasDirectChild();

    /**
     * Check the children of this class, and any children of those children, recursively, to determine if the specified component
     * is a descendant of this class.
     * @param component component in question
     * @return true if the specified component is a descendant of this class. false if not or no children are defined.
     */
    boolean isDescendant(VaadinComponent component);

    /**
     *
     * @param component component in question
     * @return true if the specified component is the parent of this class. false if not or no parent is defined.
     */
    boolean isParentOrDirectChild(VaadinComponent component);

    /**
     * Explicitly remove a child from a component and calls the child disassociate method.
     * @param component
     */
    void disassociateChild(VaadinComponent component);
    
    void disassociate();
}
