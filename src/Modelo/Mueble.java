package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mueble extends Producto {

    public enum Madera {
        PINO, ROBLE, HAYA
    };

    private LocalDate anyoFab;
    private Madera tipoMadera;
    private String estilo;

    public Mueble() {
        super();
    }


    @Override
    public String imprimirProducto() {
        DateTimeFormatter formato;
        formato = DateTimeFormatter.ofPattern("d/MMMM/yy");
        String res = super.imprimirProducto() + "el año de fabricación: " + formato.format(anyoFab) + " el tipo de madera: " + this.tipoMadera + "el estilo: " + getEstilo();
        return res;

    }

    @Override
    public void setPrecio(double precioBase) {
  
        if(this.getTipoMadera() == Madera.ROBLE){
            this.precio = (precioBase*1.10);
        } else if(this.getTipoMadera() == Madera.PINO){
            this.precio = (precioBase*0.90);
        } else{
            this.precio = (precioBase);
        }
    }

    public LocalDate getAnyoFab() {
        return anyoFab;
    }

    public void setAnyoFab(LocalDate anyoFab) {
        this.anyoFab = anyoFab;
    }

    public Madera getTipoMadera() {
        return tipoMadera;
    }

    public void setTipoMadera(Madera madera) {
        this.tipoMadera = madera;
    }
    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    
}
