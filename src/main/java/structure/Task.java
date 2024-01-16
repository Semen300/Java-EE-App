package structure;

public class Task {
    private int id;
    private String name;
    private String disc;
    private String deadline;
    private int conId;

    public Task(){}

    public Task(int id, String name, String disc, String deadline, int conId){
        this.id=id;
        this.name=name;
        this.disc=disc;
        this.deadline=deadline;
        this.conId=conId;
    }

    public void setId(int newId){this.id=newId;}
    public int getId(){return id;}

    public void setName(String newName) { this.name=newName; }
    public String getName() {return name;}

    public void setDisc(String newDisc) { this.disc=newDisc; }
    public String getDisc() {return disc;}

    public void setDeadline(String newDL) { this.deadline=newDL; }
    public String getDeadline() {return deadline;}

    public void setConId(int newConId){this.conId=newConId;}
    public int getConId(){return conId;}
}
