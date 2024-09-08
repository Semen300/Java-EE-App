package services;
import structure.Item;
import structure.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
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
                int itemID = resultSet.getInt("itemID");
                ItemService itemService = new ItemService();
                Item item = new Item(itemID, itemService.getItemNameByID(itemID));
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("conId"),
                        item,
                        resultSet.getInt("amount"),
                        resultSet.getBoolean("finished")
                );
                tasksByContract.add(task);
                }
            }catch (java.sql.SQLException e){
            e.printStackTrace();
        }
        return tasksByContract;
    }

    public Task getTaskByID(int id){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM tasks where id="+id;
        ResultSet resultSet = dataBaseService.select(request);

        try{
            if(resultSet.next()){
                int itemID = resultSet.getInt("itemID");
                ItemService itemService = new ItemService();
                Item item = new Item(itemID, itemService.getItemNameByID(itemID));
                return new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("conID"),
                        item,
                        resultSet.getInt("amount"),
                        resultSet.getBoolean("finished")
                );
            }
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean saveTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO tasks (id, name, conID, finished)" +
                "VALUES ('"+task.getId()+"','"+task.getName()+"','"+task.getConID()+"','"+task.isFinished()+"')";
        return dataBaseService.insert(request);
    }

    public boolean updateTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET name='" + task.getName()+"',con_id='"+task.getConID()+"' finished="+task.isFinished()+" WHERE id="+task.getId();
        return dataBaseService.update(request);
    }
    public boolean setFinished (int taskID){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET finished=True WHERE id="+taskID;
        return dataBaseService.update(request);
    }

    public boolean deleteTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE FROM tasks where id=" + task.getId();
        return dataBaseService.delete(request);
    }

}
