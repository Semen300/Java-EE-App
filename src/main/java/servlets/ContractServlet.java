package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import services.ContractService;
import services.TaskService;
import structure.Contract;

public class ContractServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContractService contractService=new ContractService();
        TaskService taskService = new TaskService();
        int id = Integer.parseInt(req.getParameter("conId"));
        Contract contract = contractService.getContractById(id);
        req.setAttribute("conId", contract.getId());
        req.setAttribute("conName", contract.getName());
        req.setAttribute("userLogin", contract.getExecLog());
        req.setAttribute("disc", contract.getDisc());
        req.setAttribute("deadline", contract.getDeadline());
        req.setAttribute("tasks", taskService.getTaskByContract(contract));
        getServletContext().getRequestDispatcher("/pages/contractInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
