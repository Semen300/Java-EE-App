package structure;
import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private int conID;
    private Item item;
    private int amount;
    boolean finished;

    public Task(){}

    public Task(int id, String name, int conId, Item item, int amount, boolean status){
        this.id = id;
        this.name = name;
        this.conID = conId;
        this.item = item;
        this.amount = amount;
        this.finished = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConID() {
        return conID;
    }

    public void setConID(int conId) {
        this.conID = conId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
