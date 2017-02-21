package Negocio;

import Modelo.Cliente;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientesService {

    private List<Cliente> clientes = new ArrayList();
    private ProductosService proSer;
    private VentasService venSer;

    public void introducirCliente(Cliente c) {
        try {
            clientes.add(c);
        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el cliente\n" + e.getMessage());
        }

    }

    public Cliente buscarCliente(int numeroCliente) throws Exception {
        Cliente cliente = null;
        boolean fin = false;
        try {
            for (int i = 0; i < clientes.size() && fin == false; i++) {
                if (clientes.get(i).getIdCliente() == numeroCliente) {
                    cliente = clientes.get(i);
                    fin = true;
                }
            }

            if (cliente == null) {
                throw new Exception("El cliente con id: " + numeroCliente + " no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible imprimir el cliente" + e.getMessage());
        }

        return cliente;
    }

    public void eliminarCliente(int numCliente) {
        try {
            // Al eliminar un cliente también eliminamos las ventas asociadas a el

            //Eliminamos las ventas del cliente seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : venSer.getVentas()) {
                if (v.getCliente().getIdCliente() == numCliente) {
                    ventasEliminar.add(v);

                }
            }
            venSer.getVentas().removeAll(ventasEliminar);

            //Eliminamos el cliente
            Cliente clienteBorrar = null;
            for (int i = 0; i < clientes.size() && clienteBorrar == null; i++) {
                if (clientes.get(i).getIdCliente() == numCliente) {
                    clienteBorrar = clientes.get(i);
                }
            }

            clientes.remove(clienteBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar cliente");
        }

    }

    public String imprimirTodosProductos() {
        String res = "";
        if (proSer.getProductos().isEmpty()) {
            res = "No hay productos introducidos.";

        } else {
            for (Producto p : proSer.getProductos()) {
                if (p instanceof Televisor) {
                    Televisor t = (Televisor) p;
//                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  TAMAÑO   TIPO    PULGADAS" + "\n" + t.getId() + "   " + t.getNombre() + "   " + t.getPrecio() + t.getMarca() + "   " + t.getFabricante() + "   " + t.getTamanyo() + t.getTipo() + "  " + t.getPulgadas();

                    res += String.format(" %10s  %10s  %10s  %10s  %10s  %10s  %10s   %10s", t.getId(), t.getNombre(), t.getPrecio(), t.getMarca(), t.getFabricante(), t.getTamanyo(), t.getTipo(), t.getPulgadas());
                }

                if (p instanceof Lavadora) {
                    Lavadora l = (Lavadora) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  REVOLUCIONES  CARGA" + "\n" + l.getId() + "   " + l.getNombre() + "   " + l.getPrecio() + "  " + l.getMarca() + "         " + l.getFabricante() + "   " + l.getTamanyo() + "     " + l.getRevoluciones() + "     " + l.getCarga();

                }

                if (p instanceof Mueble) {
                    DateTimeFormatter formato;
                    formato = DateTimeFormatter.ofPattern("d/MMMM/yy");
                    Mueble m = (Mueble) p;
                    res += "\n ID NOMBRE  PRECIO     AÑO FABRICACION              MADERA  ESTILO" + "\n" + m.getId() + "   " + m.getNombre() + "   " + m.getPrecio() + "  " + formato.format(m.getAnyoFab()) + "   " + m.getTipoMadera() + "     " + m.getEstilo();

                }
            }
        }
        return res;
    }

    public String imprimirTodosClientes() {
        String res = "";
        if (clientes.isEmpty()) {
            res = "No hay clientes introducidos.";

        } else {
            for (Cliente c : clientes) {

                if (c instanceof Mayorista) {
                    Mayorista m = (Mayorista) c;
                    res += "\n ID NOMBRE  RAZON SOCIAL  CIF   TIPO  DESCUENTO" + "\n" + m.getIdCliente() + "   " + m.getNombre() + "   " + m.getRazonSocial() + "   " + m.getCif() + "   " + m.getTipoMayorista() + "   " + m.getDescuento();

                }
                if (c instanceof Particular) {
                    Particular p = (Particular) c;
                    res += "\n ID NOMBRE  RAZON SOCIAL  DNI" + "\n" + p.getIdCliente() + "   " + p.getNombre() + "      " + p.getRazonSocial() + "   " + p.getDni();

                }
            }
        }
        return res;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ProductosService getProSer() {
        return proSer;
    }

    public void setProSer(ProductosService proSer) {
        this.proSer = proSer;
    }

    public VentasService getVenSer() {
        return venSer;
    }

    public void setVenSer(VentasService venSer) {
        this.venSer = venSer;
    }

    
}
