package servlets;

import services.ContractService;
import services.UserService;
import structure.Contract;
import structure.ContractM;
import structure.Worker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorText", "");
        showManager(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContractService contractService = new ContractService();
        if(req.getParameter("id")!=null) {
            int conID=Integer.parseInt(req.getParameter("id"));
            Contract contract = contractService.getContractById(conID);
            contract.setExecLogin(req.getParameter("execLogin"));
            contract.setStatus(2);
            contractService.updateContract(contract);
        }
        showManager(req, resp);
    }
    private void showManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        ContractService contractService = new ContractService();
        List<Worker> workers = userService.getWorkersByManager(req.getSession().getAttribute("userLogin").toString());
        List <ContractM> contracts = contractService.getContractsByManager(req.getSession().getAttribute("userLogin").toString());
        req.setAttribute("workers", workers);
        req.setAttribute("contracts", contracts);
        req.getRequestDispatcher("/pages/manager/main.jsp").forward(req,resp);
    }
}

