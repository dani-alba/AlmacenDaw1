package Negocio;

import Modelo.Lavadora;
import Modelo.Mueble;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductosService {

    private List<Producto> productos= new ArrayList();
    private VentasService venSer;    
    
    public void introducirProducto(Producto p) {

        try {
            productos.add(p);
        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el producto\n" + e.getMessage());
        }
    }

    public Producto buscarProducto(int np) throws Exception {
        Producto producto = null;
        boolean fin = false;
        try {
            for (int i = 0; i < productos.size() && fin == false; i++) {
                if (productos.get(i).getId() == np) {
                    producto = productos.get(i);
                    fin = true;
                }
            }

            if (producto == null) {
                throw new Exception("El producto con id " + np + " no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible imprimir el producto" + e.getMessage());
        }

        return producto;
    }

    public void elimninarProducto(int nproducto) {
        try {
            Producto productoEliminar = null;
            //Eliminamos de ventas el producto seleccionado
            venSer.eliminarVentasProductos(nproducto);

            //Eliminamos el producto
            for (Producto p : productos) {
                if (p.getId() == nproducto) {
                    productoEliminar = p;
                }
            }
            productos.remove(productoEliminar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar producto");
        }

    }

    public String imprimirTodosProductos() {
        String res = "";
        String listaProductos = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s"  , 
                "ID", "NOMBRE", "PRECIO", "MARCA", "FABRICANTE", "TAMAÑO", "TIPO", "PULGADAS");
        String listaLavadora = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s" ,
                "ID", "NOMBRE", "PRECIO", "MARCA", "FABRICANTE", "TAMAÑO", "REVOLUCIONES", "CARGA");
        String listaMueble = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s" ,
                "ID", "NOMBRE", "PRECIO", "MARCA", "AÑO FABRICACIÓN", "TIPO DE MADERA", "ESTILO");
        
        
        
        
        
        if (productos.isEmpty()) {
            res = "No hay productos introducidos.";

        } else {
            for (Producto p : productos) {
                if (p instanceof Televisor) {
                    Televisor t = (Televisor) p;

                    res += listaProductos + "\n" + String.format("%-15s %-15s %-15.2f %-15s %-15s %-15s %-15s %-15s", t.getId(), t.getNombre(), t.getPrecio(), t.getMarca(), t.getFabricante(), t.getTamanyo(), t.getTipo(), t.getPulgadas());
                }

                if (p instanceof Lavadora) {
                    Lavadora l = (Lavadora) p;
                    res += listaLavadora + "\n" + String.format("%-15s %-15s %-15.2f %-15s %-15s %-15s %-15s %-15s", l.getId(), l.getNombre(),l.getPrecio(), l.getMarca(),l.getFabricante(), l.getTamanyo(),l.getRevoluciones(), l.getCarga());

                }

                if (p instanceof Mueble) {
                    DateTimeFormatter formato;
                    formato = DateTimeFormatter.ofPattern("dd/MMMM/yy");
                    Mueble m = (Mueble) p;
                    res += listaMueble + "\n" + String.format("%-15s %-15s %-15.2f %-15s %-15s %-15s", m.getId(), m.getNombre(), m.getPrecio(), formato.format(m.getAnyoFab()), m.getTipoMadera(), m.getEstilo());

                }
            }
        }
        return res;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public VentasService getVenSer() {
        return venSer;
    }

    public void setVenSer(VentasService venSer) {
        this.venSer = venSer;
    }
    
    
}
