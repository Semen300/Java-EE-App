package services;
import structure.Item;
import structure.Task;

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
                int itemID = resultSet.getInt("itemID");
                ItemService itemService = new ItemService();
                Item item = itemService.getItemByID(itemID);
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("conId"),
                        item,
                        resultSet.getInt("amount"),
                        resultSet.getBoolean("finished"),
                        resultSet.getFloat("price")
                );
                tasksByContract.add(task);
                }
            resultSet.close();
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
                ItemService itemService = new ItemService();
                Item item = itemService.getItemByID(resultSet.getInt("itemID"));
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("conID"),
                        item,
                        resultSet.getInt("amount"),
                        resultSet.getBoolean("finished"),
                        resultSet.getFloat("price")
                );
                resultSet.close();
                return task;
            }
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public float getAllPrice(List<Task> taskList){
        float summ=0;
        for(Task task : taskList){
            summ+=task.getPrice();
        }
        return summ;
    }
    public void setFinished (int taskID){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE tasks SET finished=True WHERE id="+taskID;
        dataBaseService.update(request);
    }

    public void deleteTask(Task task){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE FROM tasks where id=" + task.getId();
        dataBaseService.delete(request);
    }

}
