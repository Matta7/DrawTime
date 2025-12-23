package views.components;

import javax.swing.*;

public class InterchangeableComponents<C1 extends JComponent, C2 extends JComponent> extends JPanel {

    private JComponent activeComponent;

    private C1 component1;
    private C2 component2;

    public InterchangeableComponents(C1 component1, C2 component2) {
        this.component1 = component1;
        this.component2 = component2;

        activeComponent = component1;

        initialize();
    }

    public JComponent getActiveComponent() {
        return activeComponent;
    }

    public C1 getComponent1() {
        return component1;
    }

    public void setComponent1(C1 component1) {
        this.component1 = component1;
        update();
    }

    public C2 getComponent2() {
        return component2;
    }

    public void setComponent2(C2 component2) {
        this.component2 = component2;
        update();
    }

    /**
     * Initialize component
     */
    private void initialize() {
        add(activeComponent);
    }

    /**
     * Update component
     */
    private void update() {
        revalidate();
        repaint();
    }

    /**
     * Swap components
     */
    public void swap() {
        remove(activeComponent);
        if (activeComponent.equals(component1)) {
            activeComponent = component2;
        } else {

            activeComponent = component1;
        }
        add(activeComponent);

        update();
    }
}
