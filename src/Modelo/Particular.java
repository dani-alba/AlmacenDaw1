package Modelo;

public class Particular extends Cliente {

    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String imprimir() {
        String res = super.imprimir() + " DNI: " + this.dni;
        return res;
    }

}
