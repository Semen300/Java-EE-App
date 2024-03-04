package structure;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Contract {

    private int id;
    private String name;
    private String disc;
    private Date deadline;
    private String execLogin;
    private String consLogin;
    private List<Task> tasks;

    public Contract(){}
    public Contract(int id, String name, String disc, Date deadline, String execLogin, String consLogin, List<Task> tasks){
        this.id=id;
        this.name=name;
        this.disc=disc;
        this.deadline=deadline;
        this.execLogin=execLogin;
        this.consLogin=consLogin;
        this.tasks=tasks;
    }

    public int getId() {
        return id;
    }
    public void setId(int newId) {
        this.id=newId;
    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name=newName;
    }

    public String getDisc() {
        return disc;
    }
    public void setDisc(String newDisc) {
        disc=newDisc;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date newPRJDL) {
        deadline=newPRJDL;
    }

    public String getExecLog() {
        return execLogin;
    }
    public void setExecLogin(String newLogin) {
        execLogin=newLogin;
    }

    public String getConsLogin() {
        return consLogin;
    }

    public void setConsLogin(String consLogin) {
        this.consLogin = consLogin;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
