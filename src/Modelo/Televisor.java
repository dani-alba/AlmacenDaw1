package Modelo;

public class Televisor extends Electrodomestico {

    public enum TipoPantalla {
        PLASMA, LED, LCD, OLED
    };

    protected double pulgadas;
    protected String tipo;
    protected TipoPantalla tipoTelevisor;

    public Televisor() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {

        if (this.pulgadas > 40) {
            this.precio = (precioBase * 1.10);
        } else if (this.getTipoTelevisor() == Televisor.TipoPantalla.PLASMA) {
            this.precio = (precioBase * 0.90);
        } else {
            this.precio = (precioBase);
        }

    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "tipo de TV: " + this.tipo + "con " + this.pulgadas + " pulgadas";
        return res;

    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public TipoPantalla getTipoTelevisor() {
        return tipoTelevisor;
    }

    public void setTipoTelevisor(TipoPantalla tipoTelevisor) {
        this.tipoTelevisor = tipoTelevisor;
    }

}
