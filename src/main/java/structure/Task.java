package structure;
import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private String disc;
    private Date deadline;
    private int conId;
    boolean active;

    public Task(){}

    public Task(int id, String name, String disc, Date deadline, int conId, boolean status){
        this.id=id;
        this.name=name;
        this.disc=disc;
        this.deadline=deadline;
        this.conId=conId;
        this.active=status;
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
    public void setDisc(String newDisc) {
        this.disc=newDisc;
    }
    public String getDisc() {
        return disc;
    }
    public void setDeadline(Date newDL) {
        this.deadline=newDL;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setConId(int newConId){
        this.conId=newConId;
    }
    public int getConId(){
        return conId;
    }
    public void setStatus(boolean status) {
        this.active = status;
    }
    public boolean isActive() {
        return active;
    }
}
