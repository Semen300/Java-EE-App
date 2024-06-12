package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import services.ContractService;
import services.TaskService;
import structure.Contract;

public class ContractServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action!=null) {
            switch (action) {
                case "add": {
                    req.getRequestDispatcher("pages/contract/add.jsp").forward(req, resp);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    ContractService contractService = new ContractService();
                    Contract contract = contractService.getContractById(id);
                    contractService.deleteContract(contract);
                    resp.sendRedirect(req.getContextPath() + "/customer");
                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("add")){
            ContractService contractService = new ContractService();
            Contract contract = new Contract();
            contract.setName(req.getParameter("name"));
            contract.setDeadline(Date.valueOf(req.getParameter("deadline")));
            contract.setConsLogin(req.getParameter("consLogin"));
            if(contract.getDeadline().getTime()<System.currentTimeMillis()){
                req.setAttribute("errorText", "Дедлайн не может быть меньше текущей даты");
                req.setAttribute("name", contract.getName());
                req.getRequestDispatcher("pages/contract/add.jsp").forward(req,resp);
            }
            else {
                contractService.saveContract(contract);
                resp.sendRedirect(req.getContextPath() + "/customer?action=show");
            }
        }
    }
}
