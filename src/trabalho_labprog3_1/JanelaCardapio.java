/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.GregorianCalendar;

/**
 *
 * @author Mattheus
 */
public class JanelaCardapio extends JFrame {

    private Integer NumMesa;
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Item> dados;
    Item iSelecionado;
    Item iPSelecionado;
    Pedido pSelecionado;
    Integer iSelecionadoIndex;
    Integer pSelecionadoIndex;

    private final JList<Item> lstItens = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> lstPedido = new JList<>(new DefaultListModel<>());
    private final JList<Item> lstItensPedido = new JList<>(new DefaultListModel<>());

    private JComboBox<String> cmbMesas = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    private JButton btnCriarPedido = new JButton("Criar Pedido");
    private JButton btnCriarItem = new JButton("Criar Item");
    private JButton btnExcluirItem = new JButton("Excluir Item");
    private JButton btnAddCardapio = new JButton("Adicionar");
    private JButton btnRmvCardapio = new JButton("Remover");
    private JButton btnFecharCardapio = new JButton("Fechar Pedido");

    private JanelaInserirItens janelaInserir = new JanelaInserirItens();

    private JLabel lblValor = new JLabel("o primeiro são os itens do pedido, e o segundo os pedidos em si");
    private JLabel lblDescricao = new JLabel("Existem 2 JLists à direita,");
    private JLabel lblMesa = new JLabel("Mesa: ");
    private JLabel lblMesa2 = new JLabel("");
    private JLabel lblHorarioI = new JLabel("organizado por mesas.");
    private JLabel lblHorarioF = new JLabel("Para criar um pedido, selecione a mesa acima e clique no botão Criar Pedido");

    private JLabel empty = new JLabel("");
    private JLabel c1 = new JLabel("Itens");
    private JLabel c2 = new JLabel("Pedido");

    JanelaCardapio(List<Item> dados) throws HeadlessException {
        super("Cardápio");
        this.dados = dados;

        lstItens.setModel(new ItemListModel(dados));
        lstItens.setPreferredSize(new Dimension(300, 0));
        add(new JScrollPane(lstItens), BorderLayout.WEST);

        JPanel cabecalho = new JPanel(new GridLayout(1, 5));
        cabecalho.add(c1);
        cabecalho.add(lblMesa);
        cabecalho.add(cmbMesas);
        cabecalho.add(btnCriarPedido);
        cabecalho.add(c2);
        add(cabecalho, BorderLayout.NORTH);

        btnExcluirItem.setEnabled(false);

        JPanel info = new JPanel(new BorderLayout());

        JPanel desc = new JPanel(new GridLayout(5, 1));
        desc.add(lblMesa2);
        desc.add(lblDescricao);
        desc.add(lblValor);
        desc.add(lblHorarioI);
        desc.add(lblHorarioF);

        info.add(desc, BorderLayout.CENTER);
        info.add(btnAddCardapio, BorderLayout.SOUTH);

        add(new JScrollPane(info), BorderLayout.CENTER);

        JPanel c = new JPanel(new BorderLayout());

        lstItensPedido.setPreferredSize(new Dimension(200, 0));
        lstPedido.setPreferredSize(new Dimension(200, 0));

        JPanel btns = new JPanel(new GridLayout(1, 2));

        btns.add(btnRmvCardapio);
        btns.add(btnFecharCardapio);

        c.add(btns, BorderLayout.SOUTH);

        c.add(new JScrollPane(lstItensPedido), BorderLayout.CENTER);
        c.add(new JScrollPane(lstPedido), BorderLayout.EAST);

        add(new JScrollPane(c), BorderLayout.EAST);

        JPanel botoes = new JPanel(new GridLayout(1, 3));
        botoes.add(btnCriarItem);
        botoes.add(btnExcluirItem);

        add(botoes, BorderLayout.SOUTH);

        lstItens.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                iSelecionado = lstItens.getSelectedValue();
                if (iSelecionado == null) {
                    btnExcluirItem.setEnabled(false);
                    return;
                }
                btnExcluirItem.setEnabled(true);
                double preco = iSelecionado.getPreco();
                String descricao = iSelecionado.getDescricao();
                lblValor.setText("Preco: R$" + Double.toString(preco));
                lblDescricao.setText("Descrição: " + descricao);
                lblHorarioI.setText("");
                lblHorarioF.setText("");
                lblMesa2.setText("");
            }
        });

        cmbMesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumMesa = cmbMesas.getSelectedIndex();

            }
        });

        lstItensPedido.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnExcluirItem.setEnabled(true);
                btnFecharCardapio.setEnabled(true);
                iPSelecionado = lstItensPedido.getSelectedValue();

            }
        });

        lstPedido.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                pSelecionado = lstPedido.getSelectedValue();
                pSelecionadoIndex = lstPedido.getSelectedIndex();

                if (pSelecionado == null) {
                    return;
                }

                NumMesa = pSelecionado.getMesa();
                Integer numIndex = NumMesa;

                if (pedidos.get(pSelecionadoIndex).getItens() != null) {
                    lstItensPedido.setModel(new ItemListModel(pedidos.get(pSelecionadoIndex).getItens()));
                } else {
                    lstItensPedido.setModel(new DefaultListModel<>());

                }
                if (pedidos.get(pSelecionadoIndex).getAberto() == 1) {
                    lblMesa2.setText("Mesa: " + numIndex.toString());
                    lblHorarioI.setText("Horário de abertura do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioI());
                    lblHorarioF.setText("Mesa ainda em atendimento");
                    lblDescricao.setText("Itens pedidos: " + pedidos.get(pSelecionadoIndex).getNomesItens());
                    lblValor.setText("Total: R$" + pedidos.get(pSelecionadoIndex).calcularTotal());
                }
                if (pedidos.get(pSelecionadoIndex).getAberto() == 2) {
                    lblMesa2.setText("Mesa(fechada): " + numIndex.toString());
                    lblHorarioI.setText("Horário de abertura do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioI());
                    lblHorarioF.setText("Horário de fechamento do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioF());
                    lblDescricao.setText("Itens pedidos: " + pedidos.get(pSelecionadoIndex).getNomesItens());
                    lblValor.setText("Total: R$" + pedidos.get(pSelecionadoIndex).calcularTotal());
                }

            }
        });

        btnCriarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumMesa = cmbMesas.getSelectedIndex();
                Integer numIndex = NumMesa;

                Date horaI = new GregorianCalendar().getTime();

                Pedido p = new Pedido();

                p.setAberto(1);
                p.setHorarioI(horaI);
                p.setHorarioF(null);
                p.setMesa(NumMesa + 1);
                p.setTotal(0);
                p.setItens(null);

                pedidos.add(p);

                lstPedido.setModel(new PedidoListModel(pedidos));
                
                
                lstPedido.setSelectedIndex(0);
                
                pSelecionado = lstPedido.getSelectedValue();
                pSelecionadoIndex = lstPedido.getSelectedIndex();

                
                lblMesa2.setText("Mesa: " + numIndex.toString());
                lblHorarioI.setText("Horário de abertura do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioI());
                lblHorarioF.setText("Mesa ainda em atendimento");
                lblDescricao.setText("Itens pedidos: " + pedidos.get(pSelecionadoIndex).getNomesItens());
                lblValor.setText("Total: R$" + pedidos.get(pSelecionadoIndex).calcularTotal());

            }
        });

        btnAddCardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> lst = new ArrayList<Item>();
                iSelecionado = lstItens.getSelectedValue();

                pSelecionado = lstPedido.getSelectedValue();
                pSelecionadoIndex = lstPedido.getSelectedIndex();

                if (iSelecionado == null) {
                    return;
                }

                NumMesa = cmbMesas.getSelectedIndex();

                if (pedidos.get(pSelecionadoIndex).getItens() != null) {
                    lstItensPedido.setModel(new ItemListModel(pedidos.get(pSelecionadoIndex).getItens()));
                    lst = pedidos.get(pSelecionadoIndex).getItens();
                }

                if (pedidos.get(pSelecionadoIndex).getAberto() == 1) {
                    lst.add(iSelecionado);
                    pedidos.get(pSelecionadoIndex).setItens(lst);
                    lstItensPedido.setModel(new ItemListModel(pedidos.get(pSelecionadoIndex).getItens()));
                }
                Integer numIndex = NumMesa + 1;

                if (pedidos.get(pSelecionadoIndex).getAberto() == 1) {
                    lblMesa2.setText("Mesa: " + numIndex.toString());
                    lblHorarioI.setText("Horário de abertura do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioI());
                    lblHorarioF.setText("Mesa ainda em atendimento");
                    lblDescricao.setText("Itens pedidos: " + pedidos.get(pSelecionadoIndex).getNomesItens());
                    lblValor.setText("Total: R$" + pedidos.get(pSelecionadoIndex).calcularTotal());
                }
            }
        });

        btnRmvCardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pSelecionado = lstPedido.getSelectedValue();
                pSelecionadoIndex = lstPedido.getSelectedIndex();
                iPSelecionado = lstItensPedido.getSelectedValue();
                NumMesa = pSelecionado.getMesa();
                Integer numIndex = NumMesa;

                if (pSelecionado == null) {
                    return;
                }
                if (iPSelecionado == null) {
                    return;
                }

                if (pedidos.get(pSelecionadoIndex).getAberto() == 1) {
                    if (lstItensPedido.isSelectionEmpty()) {
                        return;
                    } else {
                        pedidos.get(pSelecionadoIndex).getItens().remove(lstItensPedido.getSelectedValue());
                        lstItensPedido.clearSelection();
                        lstItensPedido.updateUI();
                    }
                    lblMesa2.setText("Mesa: " + numIndex.toString());
                    lblHorarioI.setText("Horário de abertura do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioI());
                    lblHorarioF.setText("Mesa ainda em atendimento");
                    lblDescricao.setText("Itens pedidos: " + pedidos.get(pSelecionadoIndex).getNomesItens());
                    lblValor.setText("Total: R$" + pedidos.get(pSelecionadoIndex).calcularTotal());
                }
            }
        });

        btnFecharCardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pSelecionado = lstPedido.getSelectedValue();
                pSelecionadoIndex = lstPedido.getSelectedIndex();

                NumMesa = pSelecionado.getMesa();
                Integer numIndex = NumMesa;

                pedidos.get(pSelecionadoIndex).setAberto(2);
                Date horaF = new GregorianCalendar().getTime();
                pedidos.get(pSelecionadoIndex).setHorarioF(horaF);

                lblMesa2.setText("Mesa(fechada): " + numIndex.toString());
                lblHorarioI.setText("Horário de abertura do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioI());
                lblHorarioF.setText("Horário de fechamento do pedido: " + pedidos.get(pSelecionadoIndex).getHorarioF());
                lblDescricao.setText("Itens pedidos: " + pedidos.get(pSelecionadoIndex).getNomesItens());
                lblValor.setText("Total: R$" + pedidos.get(pSelecionadoIndex).calcularTotal());
            }
        });

        btnCriarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaInserir.setLocationRelativeTo(null);
                janelaInserir.setVisible(true);
                janelaInserir.solicitaNovoItem();
            }
        });
        btnExcluirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstItens.isSelectionEmpty()) {
                    return;
                } else {
                    dados.remove(lstItens.getSelectedValue());
                    lstItens.clearSelection();
                    lstItens.updateUI();
                }
            }
        });
        janelaInserir.setJanelaCardapio(this);

    }

    void adicionaDados(Item i) {
        dados.add(i);
        lstItens.updateUI();
        janelaInserir.setVisible(false);
    }

}
