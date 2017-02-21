
package Modelo;

import java.util.ArrayList;
import java.util.List;



public abstract class Cliente {
   protected  int idCliente;
   protected  String nombre;
   protected  String razonSocial;
   protected  List<Venta> compras;
   private static int numClientes;
   
   Cliente(){
       numClientes++;
       idCliente=numClientes;
       compras= new ArrayList();
   }
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public List<Venta> getCompras() {
        return compras;
    }

    public void setCompras(List<Venta> compras) {
        this.compras = compras;
    }
   
   public String imprimir(){
       //id, nombre, razon social
      String res = "ID: "+this.idCliente+ " nombre: "+this.nombre+" razón social: "+this.razonSocial;
      return res;
       
   }

   
    
}
