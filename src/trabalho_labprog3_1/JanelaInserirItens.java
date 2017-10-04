/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mattheus
 */
public class JanelaInserirItens extends JFrame {

    private final JLabel lblNome = new JLabel("Nome");
    private final JLabel lblPreco = new JLabel("Preço");
    private final JLabel lblDesc = new JLabel("Descrição");

    private final JTextField txtNome = new JTextField(30);
    private final JTextField txtPreco = new JTextField(30);
    private final JTextField txtDesc = new JTextField(30);

    private final JButton btnSalvar = new JButton("Salvar");
    private JanelaCardapio janelaCardapio;

    public JanelaInserirItens() throws HeadlessException {
        super("Criar novo item");

        setResizable(false);
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel principal = new JPanel(new GridLayout(3, 2));

        principal.add(lblNome);
        principal.add(txtNome);
        principal.add(lblDesc);
        principal.add(txtDesc);
        principal.add(lblPreco);
        principal.add(txtPreco);
        
        add(principal, BorderLayout.CENTER);
        add(btnSalvar, BorderLayout.SOUTH);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtNome.getText().isEmpty() && !txtPreco.getText().isEmpty() && !txtDesc.getText().isEmpty()) {
                    Item i = new Item();
                    i.setNome(txtNome.getText());
                    i.setPreco(Double.parseDouble(txtPreco.getText()));
                    i.setDescricao(txtDesc.getText());
                    janelaCardapio.adicionaDados(i);
                }
            }
        });
    }

    public void setJanelaCardapio(JanelaCardapio janela) {
        this.janelaCardapio = janela;
    }

    void solicitaNovoItem() {
        setLocationRelativeTo(null);
        setVisible(true);
        txtNome.setText("");
        txtNome.requestFocus();
        txtPreco.setText("");
        txtDesc.setText("");

    }
}



