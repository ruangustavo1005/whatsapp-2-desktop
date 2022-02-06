package controller;

import java.util.ArrayList;
import view.ViewCadastroConversaPrivada;

/**
 * @author Leonardo & Ruan
 */
public class ControllerCadastroConversaPrivada extends ControllerBase<ViewCadastroConversaPrivada> {

    @Override
    protected ViewCadastroConversaPrivada getInstanceView() {
        return new ViewCadastroConversaPrivada(new ArrayList<>());
    }

}