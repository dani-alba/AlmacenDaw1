package Negocio;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentasService {

    private List<Venta> ventas = new ArrayList();
    private ClientesService cliSer;
    private ProductosService proSer;

    public void introducirVenta(int ncliente, int nproducto, String vend) {

        try {
            Cliente c = cliSer.buscarCliente(ncliente);
            if (c == null) {
                throw new RuntimeException("El cliente no existe.");
            }

            Producto p = proSer.buscarProducto(nproducto);
            if (p == null) {
                throw new RuntimeException("El producto no existe.");
            }

            Venta v = new Venta();
            v.setCliente(c);
            v.setVendedor(vend);
            v.setProducto(p);
            v.setPrecioVenta(); //calcula el precio de la venta segun el cliente-mayorista

            ventas.add(v);

            c.getCompras().add(v);
            p.getVentas().add(v);

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible introducir la venta" + e.getMessage());
        }

    }

    public Venta buscarVenta(int nv) {
        Venta venta;
        boolean fin = false;
        try {

            venta = null;
            for (int i = 0; i < ventas.size() && fin == false; i++) {
                if (ventas.get(i).getIdVenta() == nv) {
                    venta = ventas.get(i);
                    fin = true;
                }
            }
            if (venta == null) {
                throw new Exception("La venta con id: " + nv + " no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible imprimir la venta" + e.getMessage());
        }

        return venta;
    }

    public void eliminarVenta(int nv) {

        try {

            Venta ventaBorrar = null;
            for (int i = 0; i < ventas.size() && ventaBorrar == null; i++) {

                if (ventas.get(i).getIdVenta() == nv) {
                    ventaBorrar = ventas.get(i);

                }
            }
            if (ventaBorrar == null) {
                // hacemos saltar una excepcion que nos lleva directamente al catch
                throw new Exception("No existe ninguna venta con ese Id");
            }
            // este código solo se ejecuta si todo va bien
            ventas.remove(ventaBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar la venta");
        }

    }

    public String imprimirtodasVentas() {
        String res = "";
        if (ventas.isEmpty()) {
            res = "No hay ventas introducidas.";

        } else {
            for (Venta v : ventas) {
                res += "\n ID VENTA VENDEDOR  CLIENTE PRODUCTO PRECIO VENTA" + "\n" + v.getIdVenta() + "   " + v.getVendedor() + "   " + v.getCliente().getIdCliente() + "  " + v.getProducto().getId() + "   " + v.getPrecioVenta();

            }
        }
        return res;
    }

    public boolean eliminarVentasProductos(int nproducto) {
        List<Venta> ventasEliminar = new ArrayList();
        for (Venta v : this.ventas) {
            if (v.getProducto().getId() == nproducto) {
                ventasEliminar.add(v);

            }
        }
        return this.ventas.removeAll(ventasEliminar);
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public ClientesService getCliSer() {
        return cliSer;
    }

    public void setCliSer(ClientesService cliSer) {
        this.cliSer = cliSer;
    }

    public ProductosService getProSer() {
        return proSer;
    }

    public void setProSer(ProductosService proSer) {
        this.proSer = proSer;
    }

}
