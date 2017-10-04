package trabalho_labprog3_1;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Mattheus
 */
public class Pedido {

    private Integer mesa;
    private List<Item> itens;
    private float total;
    private Date horarioI;
    private Date horarioF;
    private Integer aberto = 0;

    public Pedido() {
    }

    public Pedido(Integer mesa, List<Item> itens, float total) {
        this.mesa = mesa;
        this.itens = itens;
        this.total = total;
    }

    public Pedido(Integer mesa, List<Item> itens, float total, Date horarioI, Date horarioF) {
        this.mesa = mesa;
        this.itens = itens;
        this.total = total;
        this.horarioI = horarioI;
        this.horarioF = horarioF;
    }

    public Integer getAberto() {
        return aberto;
    }

    public void setAberto(Integer aberto) {
        this.aberto = aberto;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public List<Item> getItens() {
        return this.itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public String getNomesItens() {
        String s = "";
        if (this.itens != null) {
            for (Item i : itens) {
                s += i.getNome() + " - ";
            }
        } else {
            s = "Não há pedidos nesta mesa";
        }
        return s;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public double calcularTotal() {
        double d = 0;
        if (this.itens != null) {
            for (Item i : itens) {
                d += i.getPreco();
            }
        }
        return d;

    }

    public Date getHorarioI() {
        return horarioI;
    }

    public void setHorarioI(Date horarioI) {
        this.horarioI = horarioI;
    }

    public Date getHorarioF() {
        return horarioF;
    }

    public void setHorarioF(Date horarioF) {
        this.horarioF = horarioF;
    }

    @Override
    public String toString() {
        return "Mesa: " + this.mesa.toString();
    }

}
