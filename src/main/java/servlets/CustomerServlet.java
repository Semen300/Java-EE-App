package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import services.ContractService;
import structure.Contract;

public class CustomerServlet extends  HttpServlet{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action!=null){
            switch(action) {
                case "add": {
                    req.getRequestDispatcher("pages/customer/add.jsp").forward(req, resp);
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
                case "exit": {
                    resp.sendRedirect(req.getContextPath() + "/login?action=login");
                    break;
                }
                default: {
                    ContractService contractService = new ContractService();
                    List<Contract> Contracts = contractService.getContractsByCustomer(req.getSession().getAttribute("userLogin").toString());
                    req.setAttribute("contracts", Contracts);
                    req.getRequestDispatcher("/pages/customer/main.jsp").forward(req, resp);
                }
            }
        }
        else{
            ContractService contractService = new ContractService();
            List<Contract> Contracts = contractService.getContractsByCustomer(req.getSession().getAttribute("userLogin").toString());
            req.setAttribute("contracts", Contracts);
            req.getRequestDispatcher("/pages/customer/main.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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
        else if(req.getParameter("action").equals("add_task")){
         //Дописать обавление тасков
        }
    }
}
