import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
	Declarar firma de metodos que seran sobrescritos
*/
public interface InterfazMetodos extends Remote {
    //El nombre bajo el cual el registro conoce a este servicio
    static final String SERVICE_NAME = "Tienda Virtual";
    public ArrayList<Articulo> consulta() throws RemoteException;
    public boolean adquisicion(int id) throws RemoteException;
}