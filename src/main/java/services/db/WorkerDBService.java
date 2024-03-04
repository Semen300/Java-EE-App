package services.db;
import structure.Worker;
import java.sql.ResultSet;

public class WorkerDBService {

    public ResultSet allData(){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * from rabotyagi";
        return dataBaseService.select(request);
    }

    public ResultSet select(String login){
        DataBaseService dataBaseService=new DataBaseService();
        String request = "SELECT login, fio from rabotyagi WHERE login='"+login+"'";
        return dataBaseService.select(request);
    }
    public Boolean create(Worker worker){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO rabotyagi (login, password, fio)" +
                "VALUES ('"+worker.getLogin()+"', '"+worker.getPassword()+"','"+worker.getFio()+"')";
        return dataBaseService.insert(request);
    }

    public Boolean update(Worker worker){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE rabotyagi SET password='"+worker.getPassword()+"', fio='"+worker.getFio()+"' WHERE login='"+worker.getLogin()+"'";
        return dataBaseService.update(request);
    }

    public Boolean delete(Worker worker){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE from rabotyagi where login=" + worker.getLogin();
        return dataBaseService.delete(request);
    }
}
