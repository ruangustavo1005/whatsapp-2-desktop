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

    public void updateConversa(Conversa conversa) {
        int index = IntStream.range(0, this.getRowCount())
                .filter(i -> this.getConversas().get(i).getId().equals(conversa.getId()))
                .findFirst()
                .getAsInt();
        
        if (index >= 0) {
            this.getConversas().get(index).setQtdMensagensNaoLidas(conversa.getQtdMensagensNaoLidas());
            this.fireTableRowsUpdated(index, index);
        }
    }
    
    @Override
    public int getRowCount() {
        return this.getConversas().size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return this.getConversas().get(rowIndex).getTitulo();
            case 1: return this.getConversas().get(rowIndex).getQtdMensagensNaoLidas();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Conversa";
            case 1: return "Notificações";
            default: return null;
        }
    }
    
}