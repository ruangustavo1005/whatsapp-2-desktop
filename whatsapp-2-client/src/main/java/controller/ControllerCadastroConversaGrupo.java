package controller;

import java.util.ArrayList;
import view.ViewCadastroConversaGrupo;

/**
 * @author Leonardo & Ruan
 */
public class ControllerCadastroConversaGrupo extends ControllerBase<ViewCadastroConversaGrupo> {

    @Override
    protected ViewCadastroConversaGrupo getInstanceView() {
        return new ViewCadastroConversaGrupo(new ArrayList<>());
    }

}