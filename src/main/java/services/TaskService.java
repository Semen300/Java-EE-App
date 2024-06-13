package services;
import structure.Task;
import structure.Contract;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public List<Task> getTaskByContract(int id){
        List<Task> tasksByContract = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM tasks WHERE conID="+id;
        ResultSet resultSet = dataBaseService.select(request);
        try {
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("conId"),
                        resultSet.getBoolean("finished")
                );
                tasksByContract.add(task);
                }
            }catch (java.sql.SQLException e){}
        return tasksByContract;
    }

    public Task getTaskByID(int id){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM tasks where id="+id;
        ResultSet resultSet = dataBaseService.select(request);

        try{
            if(resultSet.next()){
                return new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("conID"),
                        resultSet.getBoolean("finished")
                );
            }
        } catch (java.sql.SQLException e){}
        return null;
    }
    public boolean saveTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO tasks (id, name, con_id, finished)" +
                "VALUES ('"+task.getId()+"','"+task.getName()+"','"+task.getConId()+"','"+task.isFinished()+"')";
        return dataBaseService.insert(request);
    }

    public boolean updateTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET name='" + task.getName()+"',con_id='"+task.getConId()+"' finished="+task.isFinished()+" WHERE id="+task.getId();
        return dataBaseService.update(request);
    }
    public boolean setFinished (int taskID){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET finished=True WHERE id="+taskID;
        return dataBaseService.update(request);
    }

    public boolean deleteTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE FROM tasks where =" + task.getId();
        return dataBaseService.delete(request);
    }
}
