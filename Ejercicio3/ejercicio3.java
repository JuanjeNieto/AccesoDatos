import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;

public class ejercicio3 {
	private static final String VERDE = "\u001B[32m";
	private static final String REINICIAR_COLOR = "\u001B[0m";

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
						mostrarInformacionRuta(scanner);
						break;
					case 2:
						comprobarExistencia(scanner);
						break;
					case 3:
						System.out.println("1. Crear archivo");
						System.out.println("2. Crear directorio");
						System.out.print("Opción: ");
						int opcionCreacion = scanner.nextInt();
						scanner.nextLine();
						if (opcionCreacion == 1) {
							crearArchivo(scanner);
						} else if (opcionCreacion == 2) {
							crearDirectorio(scanner);
						} else {
							System.out.println("Opción no válida.");
						}
						break;
					case 4:
						eliminarArchivoODirectorio(scanner);	

						break;
					case 5:
						copiarArchivo(scanner);
						break;
					case 6:
						moverRenombrarArchivo(scanner);
						break;
					case 7:
						leerArchivoTexto(scanner);
						break;
					case 8:
						escribirArchivoTexto(scanner);
						break;
					case 9:
						listarContenidoDirectorio(scanner);
						break;
					case 10:
						compararRutas(scanner);
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

	public static void mostrarInformacionRuta(Scanner scanner) {

    System.out.print("Introduce la ruta: ");

    scanner.nextLine();
    String rutaStr = scanner.nextLine();

    Path ruta = Paths.get(rutaStr).toAbsolutePath();

	System.out.println("Nombre: " + ruta.getFileName());
	System.out.println("Ruta absoluta: " + ruta.toAbsolutePath());
	System.out.println("Padre: " + ruta.getParent());
	System.out.println("Número de elementos: " + ruta.getNameCount());
}

	public static void comprobarExistencia(Scanner scanner) {
		System.out.print("Introduce la ruta: ");
		scanner.nextLine();
		String rutaStr = scanner.nextLine();

		Path ruta = Paths.get(rutaStr).toAbsolutePath();

		if (ruta.toFile().exists()) {
			System.out.println("La ruta existe.");
			if (ruta.toFile().isFile()) {
				System.out.println("Es un archivo.");
			} else if (ruta.toFile().isDirectory()) {
				System.out.println("Es un directorio.");
			}
		} else {
			System.out.println("La ruta no existe.");
		}
	}

	public static void crearArchivo(Scanner scanner) {
		System.out.print("Ruta del archivo: ");
		String rutaStr = scanner.nextLine();
		Path ruta = Paths.get(rutaStr).toAbsolutePath();

		try {
			Path padre = ruta.getParent();
			if (padre != null) {
				Files.createDirectories(padre);
			}
			Files.createFile(ruta);
			mostrarExito("Archivo creado correctamente.");
			System.out.println("Ruta absoluta: " + ruta);
		} catch (IOException e) {
			System.out.println("Error al crear el archivo: " + e.getMessage());
		}
	}

	public static void crearDirectorio(Scanner scanner) {
		System.out.print("Ruta del directorio: ");
		String rutaStr = scanner.nextLine();
		Path ruta = Paths.get(rutaStr).toAbsolutePath();

		try {
			boolean yaExistia = Files.isDirectory(ruta);
			Files.createDirectories(ruta);
			mostrarExito(yaExistia
					? "El directorio ya existía."
					: "Directorio creado correctamente.");
			System.out.println("Ruta absoluta: " + ruta);
		} catch (IOException e) {
			System.out.println("Error al crear el directorio: " + e.getMessage());
		}
	}

	public static void eliminarArchivoODirectorio(Scanner scanner) {
		System.out.print("Introduce la ruta del archivo o directorio a eliminar: ");
		scanner.nextLine();
		String rutaStr = scanner.nextLine();

		Path ruta = Paths.get(rutaStr).toAbsolutePath();

		try {
			if (Files.exists(ruta)) {
				Files.deleteIfExists(ruta);
				mostrarExito("Eliminado correctamente: " + ruta);
			} else {
				System.out.println("La ruta no existe.");
			}
		} catch (IOException e) {
			System.out.println("Error al eliminar: " + e.getMessage());
		}
	}

	public static void copiarArchivo (Scanner scanner){
		scanner.nextLine();
		System.out.print("Introduce la ruta del archivo que quieres copiar: ");
		Path origen = Paths.get(scanner.nextLine()).toAbsolutePath();

		System.out.print("Introduce la ruta de destino: ");
		Path destino = Paths.get(scanner.nextLine()).toAbsolutePath();

		try {
			Path padre = destino.getParent();
			if (padre != null) {
				Files.createDirectories(padre);
			}
			Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
			mostrarExito("Archivo copiado correctamente.");
			System.out.println("Ruta de destino: " + destino);
		} catch (IOException e) {
			System.out.println("Error al copiar el archivo: " + e.getMessage());
		}
	}

	public static void moverRenombrarArchivo(Scanner scanner) {
			scanner.nextLine();
			System.out.print("Introduce la ruta del archivo que quieres mover o renombrar: ");
			Path origen = Paths.get(scanner.nextLine()).toAbsolutePath();

			System.out.print("Introduce la nueva ruta o nombre: ");
			Path destino = Paths.get(scanner.nextLine()).toAbsolutePath();

			try {
				Path padre = destino.getParent();
				if (padre != null) {
					Files.createDirectories(padre);
				}
				Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
				mostrarExito("Archivo movido/renombrado correctamente.");
				System.out.println("Nueva ruta: " + destino);
			} catch (IOException e) {
				System.out.println("Error al mover/renombrar el archivo: " + e.getMessage());
			}
		}

	public static void leerArchivoTexto(Scanner scanner){
		System.out.print("Introduce la ruta del archivo de texto a leer: ");
		scanner.nextLine();
		String rutaStr = scanner.nextLine();

		Path ruta = Paths.get(rutaStr).toAbsolutePath();

		try {
			for (String linea : Files.readAllLines(ruta)) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}

	public static void escribirArchivoTexto(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Introduce la ruta del archivo de texto: ");
		Path ruta = Paths.get(scanner.nextLine()).toAbsolutePath();

		System.out.print("Introduce el texto que quieres añadir: ");
		String texto = scanner.nextLine();

		try {
			Files.write(
					ruta,
					(texto + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
					StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			mostrarExito("Texto escrito correctamente en: " + ruta);
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	private static void mostrarExito(String mensaje) {
		System.out.println(VERDE + mensaje + REINICIAR_COLOR);
	}

	public static void listarContenidoDirectorio(Scanner scanner) {
		System.out.print("Introduce la ruta del directorio: ");
		scanner.nextLine();
		String rutaStr = scanner.nextLine();

		Path ruta = Paths.get(rutaStr).toAbsolutePath();

		if (Files.isDirectory(ruta)) {
			try (DirectoryStream<Path> contenido = Files.newDirectoryStream(ruta)) {
				System.out.println("Contenido del directorio:");
				for (Path elemento : contenido) {
					System.out.println(elemento.getFileName());
				}
			} catch (IOException e) {
				System.out.println("Error al listar el contenido: " + e.getMessage());
			}
		} else {
			System.out.println("La ruta no es un directorio.");
		}
	}

	public static void compararRutas(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Introduce la primera ruta: ");
		Path primera = Paths.get(scanner.nextLine()).toAbsolutePath().normalize();

		System.out.print("Introduce la segunda ruta: ");
		Path segunda = Paths.get(scanner.nextLine()).toAbsolutePath().normalize();

		System.out.println("Primera ruta: " + primera);
		System.out.println("Segunda ruta: " + segunda);

		if (primera.equals(segunda)) {
			System.out.println("Las rutas son iguales.");
		} else {
			System.out.println("Las rutas son distintas.");
		}

		if (primera.equals(segunda)) {
			System.out.println("Las rutas son iguales; no hay relación de contención.");
		} else if (primera.startsWith(segunda)) {
			System.out.println("La primera ruta está dentro de la segunda.");
		} else if (segunda.startsWith(primera)) {
			System.out.println("La segunda ruta está dentro de la primera.");
		} else {
			System.out.println("Ninguna ruta está dentro de la otra.");
		}

		try {
			Path rutaRelativa = primera.relativize(segunda);
			String resultado = rutaRelativa.toString();
			if (resultado.isEmpty()) {
				resultado = ".";
			}
			System.out.println("Ruta relativa desde la primera hasta la segunda: "
					+ resultado);
		} catch (IllegalArgumentException e) {
			System.out.println("No se puede calcular la ruta relativa: las rutas "
					+ "pertenecen a raíces distintas.");
		}
	}
}
