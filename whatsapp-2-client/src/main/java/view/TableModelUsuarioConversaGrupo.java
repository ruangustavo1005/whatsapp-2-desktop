package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Usuario;

/**
 * @author Leonardo & Ruan
 */
public class TableModelUsuarioConversaGrupo extends AbstractTableModel {

    private ArrayList<Usuario> usuarios;

    public TableModelUsuarioConversaGrupo() {
        this.usuarios = new ArrayList<>();
    }
    
    public TableModelUsuarioConversaGrupo(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void addUsuario(Usuario usuario) {
        this.getUsuarios().add(usuario);
        int i = this.getUsuarios().indexOf(usuario);
        this.fireTableRowsInserted(i, i);
    }
    
    public void deleteUsuario(Usuario usuarioSelecionado) {
        int i = this.getUsuarios().indexOf(usuarioSelecionado);
        this.getUsuarios().remove(usuarioSelecionado);
        this.fireTableRowsDeleted(i, i);
    }
    
    @Override
    public int getRowCount() {
        return this.getUsuarios().size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.getUsuarios().get(rowIndex).getNome();
    }

    @Override
    public String getColumnName(int column) {
        return "Nome";
    }

}