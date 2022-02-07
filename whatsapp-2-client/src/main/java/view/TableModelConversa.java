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
    
    public void addNotificacaoMensagemNova(Conversa conversa) {
        int index = IntStream.range(0, this.getRowCount())
                .filter(i -> this.getConversas().get(i).getId().equals(conversa.getId()))
                .findFirst()
                .getAsInt();

        if (index >= 0) {
            this.getConversas().get(index).setMensagensNovas(this.getConversas().get(index).getMensagensNovas() + 1);
            this.fireTableRowsUpdated(index, index);
        }
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
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conversa conversa = this.getConversas().get(rowIndex);
        switch (columnIndex) {
            case 0: return conversa.getTitulo();
            case 1: return conversa.getMensagensNovas() > 0 ? conversa.getMensagensNovas() : "";
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