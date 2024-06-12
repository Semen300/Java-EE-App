package services;
import structure.Task;
import structure.Contract;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public List<Task> getTaskByContract(Contract contract){
        List<Task> tasksByContract = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM tasks WHERE conID="+contract.getId();
        ResultSet resultSet = dataBaseService.select(request);
        try {
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("disc"),
                        resultSet.getDate("deadline"),
                        resultSet.getInt("conId"),
                        resultSet.getBoolean("active")
                );
                }
            }catch (java.sql.SQLException e){}
        return tasksByContract;
    }
    public boolean saveTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO tasks (id, name, disc, deadline, con_id)" +
                "VALUES ('"+task.getId()+"','"+task.getName()+"', '"+task.getDisc()+"','"+task.getDeadline()+"','"+task.getConId()+"')";
        return dataBaseService.insert(request);
    }

    public boolean updateTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET name='" + task.getName()+"',disc='"+task.getDisc()+"',deadline='"+task.getDeadline()+"',con_id='"+task.getConId()+"' WHERE id="+task.getId();
        return dataBaseService.update(request);
    }

    public boolean deleteTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE FROM tasks where =" + task.getId();
        return dataBaseService.delete(request);
    }
}
