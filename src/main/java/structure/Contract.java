package structure;

import java.util.List;

public class Contract {

    private int id;
    private String name;
    private String disc;
    private String deadline;
    private String execLogin;

    public Contract(){}
    public Contract(int id, String name, String disc, String deadline, String execLogin){
        this.id=id;
        this.name=name;
        this.disc=disc;
        this.deadline=deadline;
        this.execLogin=execLogin;
    }

    public int getId() {return id;}
    public void setId(int newId) {this.id=newId;}

    public String getName() {return name;}
    public void setName(String newName) {name=newName;}

    public String getDisc() {return disc;}
    public void setDisc(String newDisc) {disc=newDisc;}

    public String getDeadline() {return deadline;}
    public void setDeadline(String newPRJDL) {deadline=newPRJDL;}

    public String getExecLog() {return execLogin;}
    public void setExecLogin(String newLogin) {execLogin=newLogin;}
}
