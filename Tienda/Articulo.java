import java.io.Serializable;

public class Articulo implements Serializable {
    // Importante para confirmar la misma versión
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int amount;

    public int getId(){
        return this.id;
    }
    
    public int getAmount(){
        return amount;
    }
    
    public void decrementAmount(){
        amount--;
    }
    
    public Articulo(int id, String name, int amount){
        this.amount=amount;
        this.id=id;
        this.name=name;
    }
    
    public String getAsCSV(){
        return id+","+name+","+amount;
    }
    
    public String toString(){
        return "{\n \"id\": "+String.valueOf(id)+"\n \"name\": \""+name+"\"\n \"amount\": "+String.valueOf(amount)+"\n}";
    }
}