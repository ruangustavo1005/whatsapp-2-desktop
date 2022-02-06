package controller;

import view.ViewBase;

/**
 * @author Leonardo & Ruan
 */
public abstract class ControllerBase<TypeView extends ViewBase> {

    private TypeView view;
    
    abstract protected TypeView getInstanceView();

    public void abreTela() {
        this.getView().setVisible(true);
    }
    
    public TypeView getView() {
        if (this.view == null) {
            this.view = this.getInstanceView();
            this.addActionListeners(this.view);
        }
        return this.view;
    }
    
    protected void addActionListeners(TypeView view) {
        
    }
    
}