package servlets;

import services.ContractService;
import services.TaskService;
import structure.Contract;
import structure.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WorkerServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if(action!=null){
                switch (action){
                    case "conInfo":{
                        TaskService taskService = new TaskService();
                        ContractService contractService = new ContractService();
                        int id = Integer.parseInt(req.getParameter("id"));
                        List<Task> tasks = taskService.getTaskByContract(id);
                        Contract contract = contractService.getContractById(id);
                        contract.setTasks(tasks);
                        showWorker(req, resp, contract);
                        break;
                    }
                    case "setFinished":{
                        TaskService taskService = new TaskService();
                        ContractService contractService = new ContractService();
                        int taskID = Integer.parseInt(req.getParameter("taskID"));
                        Task task = taskService.getTaskByID(taskID);
                        taskService.setFinished(taskID);
                        List<Task> tasks = taskService.getTaskByContract(task.getConID());
                        Contract contract = contractService.getContractById(task.getConID());
                        contract.setTasks(tasks);
                        if(contractService.getNumberOfUnfinishedTasks(contract.getId())==0){
                            contract.setStatus(0);
                            contractService.updateContract(contract);
                        }
                        showWorker(req, resp, contract);
                    }
                    default:{
                        showWorker(req, resp, null);
                    }
                }
            }
            else {
                showWorker(req, resp, null);
            }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        }

        private void showWorker(HttpServletRequest req, HttpServletResponse resp, Contract contract) throws javax.servlet.ServletException, java.io.IOException{
            ContractService contractService = new ContractService();
            List<Contract> workerContracts = contractService.getContractsByWorker(req.getSession().getAttribute("userLogin").toString());
            req.setAttribute("contracts", workerContracts);
            req.setAttribute("contract", contract);
            req.getRequestDispatcher("pages/worker/main.jsp").forward(req, resp);
        }
}
