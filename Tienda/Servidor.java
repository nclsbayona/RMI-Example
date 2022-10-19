import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Servidor {
	private static final int PUERTO = 1100; //El puerto debe ser el mismo que se configura en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Tienda tienda=new Tienda("articulos.txt");
        Remote remote = UnicastRemoteObject.exportObject(tienda, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        // Registrar tienda en el repositorio de RMI (Exponer servicios)
        registry.bind(InterfazMetodos.SERVICE_NAME, remote);
    }
}