public class Cuenta{
    private int balance = 0;
 
    public Cuenta(int balance) {
        this.balance = balance;
    }
 
    public void retiro(int monto) {
        this.balance -= monto;
    }
 
    public void deposito(int monto) {
        this.balance += monto;
    }
 
    public int getBalance() {
        return this.balance;
    }
}