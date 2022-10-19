public class Banco {
    public static final int MAX_CUENTA = 10;
    public static final int MAX_MONTO = 10;
    public static final int BALANCE_INICIAL = 100;
 
    private Cuenta[] cuentas = new Cuenta[MAX_CUENTA];
 
    public Banco() {
        for (int i = 0; i < cuentas.length; i++) {
            cuentas[i] = new Cuenta(BALANCE_INICIAL);
        }
    }
 
    public synchronized void transferencia(int desde, int hacia, int monto) {
        try {
            while (cuentas[desde].getBalance() < monto) {
                wait();
            }
 
            cuentas[desde].retiro(monto);
            cuentas[hacia].deposito(monto);
 
            String mensaje = "%s transferencia %d desde %s hacia %s. Total balance: %d\n";
            String threadName = Thread.currentThread().getName();
            System.out.printf(mensaje, threadName, monto, desde, hacia, getTotalBalance());
 
            notifyAll();
 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public synchronized int getTotalBalance() {
        int total = 0;
 
        for (int i = 0; i < cuentas.length; i++) {
            total += cuentas[i].getBalance();
        }
 
        return total;
    }
}