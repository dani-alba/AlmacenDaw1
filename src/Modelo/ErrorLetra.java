package Modelo;

/**
 *
 * @author daw
 */
public class ErrorLetra extends RuntimeException{

    private String dniok;
    
    public ErrorLetra(String dniok) {
        super("El dni tenía la letra mal.Dni correcto " +dniok);
        this.dniok=dniok;
    }

    public String getDniok() {
        return dniok;
    }

    public void setDniok(String dniok) {
        this.dniok = dniok;
    }

    
    
    
    
}
