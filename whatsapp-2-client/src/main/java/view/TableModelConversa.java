package view;

import java.util.ArrayList;
import java.util.stream.IntStream;
import javax.swing.table.AbstractTableModel;
import model.Conversa;

/**
 * @author Leonardo & Ruan
 */
public class TableModelConversa extends AbstractTableModel {

    private final ArrayList<Conversa> conversas;

    public TableModelConversa() {
        this.conversas = new ArrayList<>();
    }
    
    public TableModelConversa(ArrayList<Conversa> conversas) {
        this.conversas = conversas;
    }

    public ArrayList<Conversa> getConversas() {
        return conversas;
    }

    public void addConversa(Conversa conversa) {
        this.getConversas().add(conversa);
        int i = this.getConversas().indexOf(conversa);
        this.fireTableRowsInserted(i, i);
    }
    
    public void clear() {
        int i = this.getConversas().size();
        if (i > 0) {
            this.getConversas().clear();
            this.fireTableRowsDeleted(0, i - 1);
        }
    }
    
    @Override
    public int getRowCount() {
        return this.getConversas().size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.getConversas().get(rowIndex).getTitulo();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Conversa";
            default: return null;
        }
    }
    
}