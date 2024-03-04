package services;

import services.db.WorkerDBService;
import structure.Worker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WorkerService {

    public List<Worker> getAllData(){
        List<Worker> workers=new ArrayList<>();
        WorkerDBService userDBService=new WorkerDBService();
        ResultSet Users = userDBService.allData();
        try {
            while (Users.next()) {
                Worker cur= new Worker();
                cur.setLogin(Users.getString("login"));
                cur.setFio(Users.getString("fio"));
                workers.add(cur);
            }
        } catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return workers;
    }

    public Worker getWorkerByLogin(String login){
        WorkerDBService userDBService=new WorkerDBService();
        Worker user = new Worker();
        ResultSet rs = userDBService.select(login);
        if(rs!=null) {
            try {
                rs.next();
                user.setLogin(rs.getString("login"));
                user.setFio(rs.getString("fio"));
            } catch (java.sql.SQLException e) {
            }
        }
        return user;
    }
    public boolean saveWorker(Worker worker){
        WorkerDBService workerDBService = new WorkerDBService();
        HashService hashService = new HashService();
        String hashedPass = hashService.createHash(worker.getPassword());
        worker.setPassword(hashedPass);
        return workerDBService.create(worker);
    }

    public boolean updateWorker(Worker worker){
        WorkerDBService workerDBService = new WorkerDBService();
        HashService hashService = new HashService();
        String hashedPass = hashService.createHash(worker.getPassword());
        worker.setPassword(hashedPass);
        return workerDBService.update(worker);
    }
    public boolean deleteWorker(Worker worker){
        WorkerDBService workerDBService = new WorkerDBService();
        return workerDBService.delete(worker);
    }
}
