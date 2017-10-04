package trabalho_labprog3_1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Mattheus
 */
public class Trabalho_LabProg3_1 {

    public static void main(String[] args) {
        List<Item> dados = getSampleData();

        JanelaCardapio janela = new JanelaCardapio(dados);
        janela.setLocationRelativeTo(null);
        janela.setSize(1350, 600);
        janela.setLocation(0, 100);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    private static List<Item> getSampleData() {
        List<Item> itens = new ArrayList<Item>() {
            {
                add(new Item("Hambúrguer", "Pão, bife de hambúrguer, salada e batata palha", 9));
                add(new Item("X Burguer", "Pão, bife de hambúrguer, mussarela, salada e batata palha", 10));
                add(new Item("X Salada", "Pão, bife de hambúrguer, mussarela, presunto, salada e batata palha", 10.9));
                add(new Item("X Egg", "Pão, bife de hambúrguer, mussarela, presunto, ovo, salada e batata palha", 12.6));
                add(new Item("X Egg Bacon", "Pão, bife de hambúrguer, mussarela, presunto, ovo, bacon, salada e batata palha", 14.7));
                add(new Item("X Milho", "Pão, bife de hambúrguer, mussarela, presunto, milho verde, salada e batata palha", 12.6));
                add(new Item("X Egg Milho", "Pão, bife de hambúrguer, mussarela, presunto, ovo, milho verde, salada e batata palha", 13.6));
                add(new Item("X Bacon", "Pão, bife de hambúrguer, mussarela, presunto, bacon, salada e batata palha", 14));
                add(new Item("X Bacon Milho", "Pão, bife de hambúrguer, mussarela, presunto, bacon, milho verde, salada e batata palha", 14.7));
                add(new Item("X Tudo", "Pão, bife de hambúrguer, mussarela, presunto, bacon, ovo, milho verde, salada e batata palha", 15.4));
            }

        };
        return itens;
    }

}
