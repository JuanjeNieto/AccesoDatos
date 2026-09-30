import java.util.InputMismatchException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ejercicio3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion = -1;

		while (opcion != 0) {
			System.out.println("\n===== GESTOR DE ARCHIVOS ======");
			System.out.println("1. Mostrar información de una ruta ");
			System.out.println("2. Comprobar existencia y si es archivo o directorio");
			System.out.println("3. Crear un archivo o directorio");
			System.out.println("4. Eliminar un archivo o directorio");
			System.out.println("5. Copiar archivo");
			System.out.println("6. Mover o renombrar archivo");
			System.out.println("7. Leer archivo de texto");
			System.out.println("8. Escribir en archivo de texto");
			System.out.println("9. Listar contenido de un directorio");
			System.out.println("10.Comparar rutas");
			System.out.println("0. Salir");
			System.out.print("Selecciona una opcion: ");

			try {
				opcion = scanner.nextInt();

				switch (opcion) {
					case 1:
						System.out.print("Introduce la ruta: ");
						scanner.nextLine();
						String rutaStr = scanner.nextLine();

						Path ruta = Paths.get(rutaStr);

						System.out.println("Nombre: " + ruta.getFileName());
						System.out.println("Ruta absoluta: " + ruta.toAbsolutePath());
						System.out.println("Padre: " + ruta.getParent());
						System.out.println("Número de elementos: " + ruta.getNameCount());

						break;
					case 2:
						// Aqui va la funcionalidad de la opcion 2.
						System.out.println("Has seleccionado la opcion 2.");
						break;
					case 3:
						// Aqui va la funcionalidad de la opcion 3.
						System.out.println("Has seleccionado la opcion 3.");
						break;
					case 4:
						// Aqui va la funcionalidad de la opcion 4.
						System.out.println("Has seleccionado la opcion 4.");
						break;
					case 5:
						// Aqui va la funcionalidad de la opcion 5.
						System.out.println("Has seleccionado la opcion 5.");
						break;
					case 6:
						// Aqui va la funcionalidad de la opcion 6.
						System.out.println("Has seleccionado la opcion 6.");
						break;
					case 7:
						// Aqui va la funcionalidad de la opcion 7.
						System.out.println("Has seleccionado la opcion 7.");
						break;
					case 8:
						// Aqui va la funcionalidad de la opcion 8.
						System.out.println("Has seleccionado la opcion 8.");
						break;
					case 9:
						// Aqui va la funcionalidad de la opcion 9.
						System.out.println("Has seleccionado la opcion 9.");
						break;
					case 10:
						// Aqui va la funcionalidad de la opcion 10.
						System.out.println("Has seleccionado la opcion 10.");
						break;
					case 0:
						System.out.println("Saliendo del programa...");
						scanner.close();
						return;
					default:
						System.out.println("Opcion no valida. Introduce un numero del 0 al 10.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada no valida. Debes introducir un numero.");
				scanner.nextLine();
			}
		}

		scanner.close();
	}
}
