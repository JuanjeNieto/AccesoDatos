import java.io.File;

public class ejercicio1 {

    public static void main(String[] args) {
        
        String rutaStr = (args.length > 0) ? args[0] : ".";

        File ruta = new File(rutaStr);

        System.out.println("Ruta: " + rutaStr);

        if (!ruta.exists()) {
            System.out.println("La ruta no existe.");
            return;
        }

        //Información
        System.out.println("ruta absoluta: " + ruta.getAbsolutePath());
        System.out.println("Nombre: " + ruta.getName());
        System.out.println("Ruta padre: " + ruta.getParent());
        System.out.println("es directorio: " + ruta.isDirectory());
        System.out.println("es archivo: " + ruta.isFile());

        if (ruta.isDirectory()) {
            String[] contenido = ruta.list();
            System.out.println("Contenido del directorio:");
            
            if (contenido == null) {
                System.out.println("Directorio vacío");
            } else {
                for (String nombre : contenido) {

                    File elemento = new File(ruta, nombre);

                    if (elemento.isDirectory()) {
                        System.out.println("Directorio: " + nombre);
                    } else 
                        System.out.println("Fichero: " + nombre);
                }
            }
        }
        else 
            System.out.println("Tamaño del fichero: " + ruta.length() + " bytes");
    }
}