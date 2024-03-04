package services;
import services.db.TaskDBService;
import structure.Task;
import structure.Contract;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public List<Task> getTaskByContract(Contract contract){
        List<Task> tasksByContract = new ArrayList<>();
        TaskDBService taskDBService = new TaskDBService();
        ResultSet allTasks=taskDBService.allData();
        try {
            while (allTasks.next()) {
                Task task = new Task();
                task.setId(allTasks.getInt("id"));
                task.setName(allTasks.getString("name"));
                task.setDisc(allTasks.getString("disc"));
                task.setDeadline(allTasks.getDate("deadline"));
                task.setConId(allTasks.getInt("conId"));
                if(task.getConId()==contract.getId()){
                    tasksByContract.add(task);
                }
            }
        }catch (java.sql.SQLException e){}
        return tasksByContract;
    }
    public void saveTask(Task task){
        TaskDBService taskDBService = new TaskDBService();
        taskDBService.create(task);
    }

    public void updateTask(Task task){
        TaskDBService taskDBService = new TaskDBService();
        taskDBService.update(task);
    }

    public void deleteTask(Task task){
        TaskDBService taskDBService=new TaskDBService();
        taskDBService.delete(task);
    }
}
