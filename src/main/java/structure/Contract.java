package structure;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Contract {

    private int id;
    private String name;
    private Date deadline;
    private String execLogin;
    private String consLogin;
    private int status;
    private List<Task> tasks;

    public Contract(){}
    public Contract(int id, String name, Date deadline, String execLogin, String consLogin, int status){
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.execLogin = execLogin;
        this.consLogin = consLogin;
        this.status = status;
    }

    public Contract(HttpServletRequest req){
        id=Integer.parseInt(req.getAttribute("id").toString());
        name = req.getAttribute("name").toString();
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


    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date newPRJDL) {
        deadline=newPRJDL;
    }

    public String getExecLogin() {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
