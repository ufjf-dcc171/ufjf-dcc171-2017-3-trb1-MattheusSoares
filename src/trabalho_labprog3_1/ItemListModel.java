/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Mattheus
 */
public class ItemListModel implements ListModel<Item>{
    
    private final List<Item> itens;
    private final List<ListDataListener> dataListener;

    public ItemListModel(List<Item> itens) {
        this.itens = itens;
        this.dataListener = new ArrayList<>();
    }
    @Override
    public int getSize() {
        return itens.size();
    }
    @Override
    public Item getElementAt(int index) {
        return itens.get(index);
    }
    @Override
    public void addListDataListener(ListDataListener l) {
        this.dataListener.add(l);
    }
    @Override
    public void removeListDataListener(ListDataListener l) {
        this.dataListener.remove(l);
    }
}
