public class TransaccionPrueba {
    public static void main(String[] args) {
        Banco banco = new Banco();
 
        for (int i = 0; i < Banco.MAX_CUENTA; i++) {
            Thread t = new Thread(new Transaccion(banco, i));
            t.start();
        }
    }
}