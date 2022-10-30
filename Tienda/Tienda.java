import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Tienda implements InterfazMetodos {

    private ArrayList<Articulo> articulos;
    private String filename;

    private void escribirArticulos() {
        try (DataOutputStream outstream = new DataOutputStream(new FileOutputStream(new File(filename),false))) {
            String body = "";
            for (Articulo articulo : articulos) {
                body += articulo.getAsCSV() + "\n";
            }
            outstream.write(body.getBytes());
            outstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    private Articulo getArticulo(int id) {
        for (Articulo articulo : articulos)
            if (articulo.getId() == id)
                return articulo;
        return null;
    }

    @Override
    public ArrayList<Articulo> consulta() throws RemoteException {
        return articulos;
    }

    @Override
    public boolean adquisicion(int id) throws RemoteException {
        boolean bought = false;
        try {
            Articulo art = this.getArticulo(id);
            if (art.getAmount() > 0) {
                art.decrementAmount();
                bought = true;
                escribirArticulos();
                if (art.getAmount() == 0)
                    articulos.remove(art);
            }
        } catch (Exception e) {

        }
        return bought;
    }

    public Tienda(String filename) {
        this.filename=filename;
        articulos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] artic = line.split(","); // use comma as separator
                Articulo articulo = new Articulo(Integer.valueOf(artic[0]), artic[1], Integer.valueOf(artic[2]));
                articulos.add(articulo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
