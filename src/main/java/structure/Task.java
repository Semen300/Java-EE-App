package structure;
import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private int conId;
    boolean finished;

    public Task(){}

    public Task(int id, String name, int conId, boolean status){
        this.id=id;
        this.name=name;
        this.conId=conId;
        this.finished=status;
    }

    public void setId(int newId){
        this.id=newId;
    }
    public int getId(){
        return id;
    }
    public void setName(String newName) {
        this.name=newName;
    }
    public String getName() {
        return name;
    }
    public void setConId(int newConId){
        this.conId=newConId;
    }
    public int getConId(){
        return conId;
    }
    public boolean isFinished() {return finished;}
    public void setFinished(boolean finished) {this.finished = finished;}
}
