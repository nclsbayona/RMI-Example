import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
	private static final String IP = "rmi-server";
	private static final int PUERTO = 1100;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
		InterfazMetodos interfaz = (InterfazMetodos) registry.lookup(InterfazMetodos.SERVICE_NAME); // Buscar en el
																									// registro...
		int eleccion, id;
		String menu = "\n\n------------------\n\n[-1] => Salir\n[0] => Consultar\n[1] => Adquirir elemento\n\nElige: ";
		try (Scanner sc = new Scanner(System.in)) {
			do {
				System.out.println(menu);

				try {
					eleccion = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					eleccion = -1;
				}

				if (eleccion != -1) {

					switch (eleccion) {
						case 0:
							System.out.println(interfaz.consulta());
							break;
						case 1:
							System.out.println("Ingrese el id del producto a comprar: ");
							id = Integer.parseInt(sc.nextLine());
							System.out.println(interfaz.adquisicion(id) ? "Compra realizada correctamente"
									: "Compra no realizada");
							break;
					}

					System.out.println("Presiona ENTER para continuar");
					sc.nextLine();
				}
			} while (eleccion != -1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}