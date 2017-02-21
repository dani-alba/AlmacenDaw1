
package Modelo;

import java.util.ArrayList;
import java.util.List;



public abstract  class Producto {
    protected int id;
    protected String nombre;
    protected double precio;
    protected List<Venta> ventas;
    protected static int contador=1; 
    
    public Producto(){
        ventas= new ArrayList();
        this.id=contador;
        contador++;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public int getId() {
        return id;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    
    
    public abstract void setPrecio(double precioBase);
    
    
    
    public  String imprimirProducto(){
        String res = "El id es: "+this.id+" el precio: "+this.precio+" del objeto: "+this.nombre;
        return res;
    }
    
    
    public static String imprimirNumProductos(){
        String res = "El número de productos es: "+contador;
        return res;
    }

        
    }
    
    

