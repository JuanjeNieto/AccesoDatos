import java.io.File;
import java.text.SimpleDateFormat;

public class ejercicio2 {

    public static void main(String[] args) {

        // Si recibimos una ruta por argumentos la usamos.
        // Si no, utilizamos el directorio actual "."
        String rutaStr = (args.length > 0) ? args[0] : ".";

        File ruta = new File(rutaStr);

        // Comprobamos si existe
        if (!ruta.exists()) {
            System.out.println("Error: la ruta no existe.");
            return;
        }

        // Si la ruta es un fichero
        if (ruta.isFile()) {

            mostrarInformacion(ruta);

        // Si la ruta es un directorio
        } else if (ruta.isDirectory()) {

            File[] contenido = ruta.listFiles();

            if (contenido != null) {

                for (int i = 0; i < contenido.length; i++) {
                    mostrarInformacion(contenido[i]);
                    System.out.println("----------------------------");
                }

            }
        }
    }


    public static void mostrarInformacion(File archivo) {

        System.out.println("Nombre: " + archivo.getName());

        // Permisos
        String permisos = "";

        if (archivo.canRead()) {
            permisos += "r";
        } else {
            permisos += "-";
        }

        if (archivo.canWrite()) {
            permisos += "w";
        } else {
            permisos += "-";
        }

        if (archivo.canExecute()) {
            permisos += "x";
        } else {
            permisos += "-";
        }

        System.out.println("Permisos: " + permisos);

        // El tamaño solamente se muestra si es un fichero
        if (archivo.isFile()) {
            System.out.println("Tamaño: " + archivo.length() + " bytes");
        }

        // Fecha de última modificación
        SimpleDateFormat formato =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(
                "Última modificación: "
                + formato.format(archivo.lastModified())
        );
    }
}