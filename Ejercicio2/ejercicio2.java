import java.io.File;
import java.text.SimpleDateFormat;

public class ejercicio2 {
    public static void main(String[] args) {
       
        String rutaStr = (args.length > 0) ? args[0] : ".";

        File file = new File(rutaStr);

        if (!file.exists()) {
            System.out.println("Error: la ruta no existe.");
            return;
        }

        if (file.exists()) {
            System.out.println("Nombre del archivo o directorio: " + file.getName());
            System.out.println("Permisos: "
                    + (file.canRead() ? "r" : "-")
                    + (file.canWrite() ? "w" : "-")
                    + (file.canExecute() ? "x" : "-"));
            if (file.isFile()) {
                System.out.println("Tamaño: " + file.length() + " bytes");
            }
            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Última modificación: " + fecha.format(file.lastModified()));
        }
    }
}
