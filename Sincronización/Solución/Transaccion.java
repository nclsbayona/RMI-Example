public class Transaccion implements Runnable {
    private Banco banco;
    private int desdeCuenta;
 
    public Transaccion(Banco banco, int desdeCuenta) {
        this.banco = banco;
 
 
        this.desdeCuenta = desdeCuenta;
    }
 
    public void run() {
 
        while (true) {
            int haciaCuenta = (int) (Math.random() * Banco.MAX_CUENTA);
 
            if (haciaCuenta == desdeCuenta) continue;
 
            int monto = (int) (Math.random() * Banco.MAX_MONTO);
 
            if (monto == 0) continue;
 
            banco.transferencia(desdeCuenta, haciaCuenta, monto);
 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}