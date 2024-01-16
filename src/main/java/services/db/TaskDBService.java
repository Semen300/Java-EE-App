package services.db;

import structure.Contract;
import structure.Task;

import java.sql.ResultSet;

public class TaskDBService {

    public ResultSet allData(){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * from tasks";
        return dataBaseService.select(request);
    }
    public Boolean create(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO tasks (id, name, disc, deadline, con_id)" +
                "VALUES ('"+task.getId()+"','"+task.getName()+"', '"+task.getDisc()+"','"+task.getDeadline()+"','"+task.getConId()+"')";
        return dataBaseService.insert(request);
    }

    public Boolean update(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET name='" + task.getName()+"',disc='"+task.getDisc()+"',deadline='"+task.getDeadline()+"',con_id='"+task.getConId()+"' WHERE id="+task.getId();
        return dataBaseService.update(request);
    }

    public Boolean delete(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE from tasks where =" + task.getId();
        return dataBaseService.delete(request);
    }
}
