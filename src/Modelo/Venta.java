package Modelo;

public class Venta {

    private int idVenta;
    private String vendedor;
    private Cliente cliente;
    private Producto producto;
    private static int numVentas = 0;
    private double precioVenta;

    public Venta() {
        numVentas++;
        this.idVenta = numVentas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public static int getNumVentas() {
        return numVentas;
    }

    public static void setNumVentas(int numVentas) {
        Venta.numVentas = numVentas;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

  

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta() {
        Double precio = producto.getPrecio();
        if (cliente instanceof Mayorista) {
            Mayorista m = (Mayorista) cliente;
            precio = precio - (precio * m.getDescuento()/100);
        }
        this.precioVenta = precio;
    }
    
    public String imprimirVenta() {
        String res = "";
        res = "ID de venta:" + this.getIdVenta() + " , vendedor:" + this.getVendedor() + " , producto:" + this.producto.imprimirProducto();
        return res;
    }

}
