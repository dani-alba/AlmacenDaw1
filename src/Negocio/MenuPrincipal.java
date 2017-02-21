package Negocio;

import Modelo.Cliente;
import Modelo.ErrorLetra;
import Modelo.FormatoFechaErroneo;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.TipoMayorista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class MenuPrincipal {

    private ClientesService cliSer = new ClientesService();
    private ProductosService proSer = new ProductosService();
    private VentasService venSer = new VentasService();

    MenuPrincipal() {        
        proSer.setVenSer(venSer);
        cliSer.setProSer(proSer);
        cliSer.setVenSer(venSer);
        venSer.setCliSer(cliSer);
        venSer.setProSer(proSer);
    }

    public void inciarAplicacion() {
        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println("                ┌─────────────┐");
                System.out.println("                │    1.Productos     │");
                System.out.println("                │    2.Clientes      │");
                System.out.println("                │    3.Ventas        │");
                System.out.println("                │   0. Para Salir    │");
                System.out.println("                └─────────────┘");
                System.out.println("Introduzca la opcion");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println("                ┌───────────────────────┐");
                System.out.println("                │1.Introducir Producto               │");
                System.out.println("                │2.Dar de baja un producto           │");
                System.out.println("                │3.Imprimir los datos de un producto │");
                System.out.println("                │4.Imprimir todos los productos      │");
                System.out.println("                │0. Salir del menu                   │");
                System.out.println("                └───────────────────────┘");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();

                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    proSer.introducirProducto(p);
                }
                if (opcionProductos == 2) {
                    System.out.println("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    proSer.elimninarProducto(num);
                }
                if (opcionProductos == 3) {
                    System.out.println("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    System.out.println(proSer.buscarProducto(nprod).imprimirProducto());
                }
                if (opcionProductos == 4) {
                    System.out.println(proSer.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida" + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca precio base: ");
            precio = Double.parseDouble(sc.nextLine());

            System.out.println("                ┌─────────────────────┐");
            System.out.println("                │Introduzca el tipo de producto:  │");
            System.out.println("                │1. Mueble                        │");
            System.out.println("                │2. Lavadora                      │");
            System.out.println("                │3. Televisor                     │");
            System.out.println("                └─────────────────────┘");
            opcion = sc.nextInt();
            if (opcion == 1) {
                producto = pedirMueble();
            }
            if (opcion == 2) {
                producto = pedirLavadora();
            }
            if (opcion == 3) {
                producto = pedirTelevisor();
            }
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return producto;
    }

    public Mueble pedirMueble() {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);

        m.setTipoMadera(pedirMadera());

        System.out.println("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());

        try {
            System.out.println("Introduzca la fecha (07/febrero/17): ");
            m.setAnyoFab(this.validarFecha(sc.nextLine()));
        } catch (FormatoFechaErroneo f) {
            System.out.println(f.getMessage());
        }

        return m;

    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();

        System.out.println("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.println("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        return l;
    }

    public Televisor pedirTelevisor() {
        Televisor tv = new Televisor();
        Scanner sc = new Scanner(System.in);

        tv.setTipoTelevisor(pedirPantalla());

        System.out.println("Introduzca las pulgadas: ");
        double pulgadas = Double.parseDouble(sc.nextLine());
        tv.setPulgadas(pulgadas);

        System.out.println("Introduzca el tipo: ");
        tv.setTipo(sc.nextLine());

        return tv;
    }

    private Televisor.TipoPantalla pedirPantalla() {
        Televisor.TipoPantalla tv = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Pantalla");
            
            Televisor.TipoPantalla[] valor = Televisor.TipoPantalla.values();
            for (int i = 0; i < valor.length; i++) {
                System.out.println(i+1 + ".-" + valor[i]);
            }

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4"));

        if (opcion.equals("1")) {
            tv = Televisor.TipoPantalla.PLASMA;
        }
        if (opcion.equals("2")) {
            tv = Televisor.TipoPantalla.LED;
        }
        if (opcion.equals("3")) {
            tv = Televisor.TipoPantalla.LCD;
        }
        if (opcion.equals("4")) {
            tv = Televisor.TipoPantalla.OLED;
        }
        return tv;
    }

    private Mueble.Madera pedirMadera() {
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Madera");
            
            Mueble.Madera[] valor = Mueble.Madera.values();
            for (int i = 0; i < valor.length; i++) {
                System.out.println(i+1 + ".-" + valor[i]);
            }

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        return m;
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);

        try {
            int opcionClientes = -1;
            do {
                System.out.println("                ┌───────────────────────┐");
                System.out.println("                │1.Introducir Cliente                │");
                System.out.println("                │2.Dar de baja un cliente            │");
                System.out.println("                │3.Imprimir los datos de un cliente  │");
                System.out.println("                │4.Imprimir todos los clientes       │");
                System.out.println("                │0. Salir del menu                   │");
                System.out.println("                └───────────────────────┘");

                opcionClientes = sc.nextInt();
                if (opcionClientes == 1) {
                    Cliente c = datosClientes();
                    cliSer.introducirCliente(c);
                }
                if (opcionClientes == 2) {
                    System.out.println("Introduzca el numero de cliente: ");
                    int num = sc.nextInt();
                    cliSer.eliminarCliente(num);
                }
                if (opcionClientes == 3) {
                    System.out.println("Introduzca el numero de cliente: ");
                    int num = sc.nextInt();
                    System.out.println(cliSer.buscarCliente(num).imprimir());
                }
                if (opcionClientes == 4) {
                    System.out.println(cliSer.imprimirTodosClientes());
                }

            } while (opcionClientes != 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.menuClientes();
        }

    }

    public Cliente datosClientes() throws Exception {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        String nombre, razonSocial;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca la razon social: ");
            razonSocial = sc.nextLine();

            System.out.println("Introduzca el tipo de cliente: ");
            System.out.println("1. Mayorista");
            System.out.println("2. Particular");
            opcion = sc.nextInt();
            if (opcion == 1) {
                cliente = mayorista();
            }
            if (opcion == 2) {
                cliente = particular();
            }
            if (cliente != null) {
                cliente.setNombre(nombre);
                cliente.setRazonSocial(razonSocial);
            }

        } while (opcion != 1 && opcion != 2);

        return cliente;
    }

    private Mayorista mayorista() {
        Mayorista m = new Mayorista();
        Scanner sc = new Scanner(System.in);
        String cif, descuento;
        TipoMayorista tm;
        String opcion;

        System.out.println("Introducecl el cif: ");
        cif = sc.nextLine();
        m.setCif(cif);

        do {
            System.out.println("Introduzca el tipo de Mayorista");
            System.out.println(Arrays.toString(TipoMayorista.values()));

            opcion = sc.nextLine();
            
            TipoMayorista[] valor = TipoMayorista.values();
            for (int i = 0; i < valor.length; i++) {
                System.out.println(i+1 + ".-" + valor[i]);
            }

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4"));

        if (opcion.equals("1")) {
            tm = TipoMayorista.GRAN_SUPERFICIE;
            m.setTipoMayorista(tm);
        }
        if (opcion.equals("2")) {
            tm = TipoMayorista.TIENDA;
            m.setTipoMayorista(tm);
        }

        System.out.println("Introduce el descuento: ");
        descuento = sc.nextLine();
        m.setDescuento(Double.parseDouble(descuento));

        return m;
    }

    private Particular particular() {
        Particular p = new Particular();
        Scanner sc = new Scanner(System.in);
        String dni, dniBueno, letra;

        int operacion, dniSize;

        System.out.println("Introducecl el dni: ");
        dni = sc.nextLine();
        dniSize = dni.length();
        letra = letraDni(dni);

        if (dniSize == 9 || dniSize == 8) {
            operacion = validarDni(dni);
            if (operacion == 1) {
                dniBueno = dni.concat(letra);
                p.setDni(dniBueno);
                System.out.println("Le faltaba la última letra");
                System.out.println(dniBueno);
            }

            if (operacion == 2) {
                dniBueno = dni.substring(0, 8).concat(letra);
                p.setDni(dniBueno);
                if (!letra.equalsIgnoreCase(dni.substring(8, 9))) {
                    throw new ErrorLetra(dniBueno);
                }
                System.out.println(dniBueno);
            }
        } else {
            System.out.println("El formato de dni no es el adecuado. Por "
                    + "favor, introduzca 8 dígitos y 1 letra");
        }
        return p;
    }

    private int validarDni(String dni) {
        int operacion = -1;

        if (dni.length() == 8 && soloNumeros(dni)) {
            operacion = 1;
        }
        if (dni.length() == 9 && soloNumeros(dni)) {
            operacion = 2;
        }

        return operacion;
    }

    private boolean soloNumeros(String dni) {
        int restar = 0;
        boolean operacion = true;

        if (dni.length() == 9) {
            restar = 1;
        }

        for (int i = 0; i < dni.length() - restar; i++) {
            if (Character.isDigit(dni.charAt(i)) == false) {
                operacion = false;
            }
        }

        return operacion;
    }

    private String letraDni(String dni) {
        int miDni = Integer.parseInt(dni.substring(0, 8)), resto = 0;
        String miLetra;
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X",
            "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        resto = miDni % 23;
        miLetra = asignacionLetra[resto];

        return miLetra;
    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println("                ┌───────────────────────┐");
                System.out.println("                │1.Introducir Venta                  │");
                System.out.println("                │2.Dar de baja una venta             │");
                System.out.println("                │3.Imprimir los datos de una venta   │");
                System.out.println("                │4.Imprimir todas las ventas         │");
                System.out.println("                │0. Salir del menu                   │");
                System.out.println("                └───────────────────────┘");
                opcionVentas = sc.nextLine();

                if (opcionVentas.equals("1")) {
                    System.out.println("Introduce el número de cliente.");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el número de producto.");
                    int np = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el nombre del vendedor: ");
                    String v = sc.nextLine();
                    venSer.introducirVenta(nv, np, v);
                }
                if (opcionVentas.equals("2")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    venSer.eliminarVenta(nv);
                }
                if (opcionVentas.equals("3")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println(venSer.buscarVenta(nv).imprimirVenta());
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(venSer.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuVentas();
        }

    }

    private LocalDate validarFecha(String fecha) {
        DateTimeFormatter formato;
        LocalDate fech = null;
        formato = DateTimeFormatter.ofPattern("dd/MMMM/yy");

        try {
            fech = LocalDate.parse(fecha, formato);
        } catch (DateTimeParseException f) {
            throw new FormatoFechaErroneo("Formato fecha erroneo");
        }

        return fech;
    }

}
