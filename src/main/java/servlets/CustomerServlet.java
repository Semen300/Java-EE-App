package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import services.ContractService;
import structure.Contract;

public class CustomerServlet extends  HttpServlet{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action!=null){
            switch(action) {
                case "new": {
                    resp.sendRedirect(req.getContextPath() + "/contract?action=add");
                    break;
                }//переход к сервлету контракта, добавление
                case "delete": {
                    String id = req.getParameter("id");
                    resp.sendRedirect(req.getContextPath() + "/contract?action=delete&id=" + id);
                    break;
                }//переход к сервлету контракта, удаление
                case "exit": {
                    resp.sendRedirect(req.getContextPath() + "/login?action=login");
                    break;
                }//заввершение сесии, возврат к логину
                default: {
                    ContractService contractService = new ContractService();
                    List<Contract> Contracts = contractService.getContractsByConsumer(req.getSession().getAttribute("userLogin").toString());
                    req.setAttribute("contracts", Contracts);
                    req.getRequestDispatcher("/pages/customer/main.jsp").forward(req, resp);
                }
            }
        }
        else{
            ContractService contractService = new ContractService();
            List<Contract> Contracts = contractService.getContractsByConsumer(req.getSession().getAttribute("userLogin").toString());
            req.setAttribute("contracts", Contracts);
            req.getRequestDispatcher("/pages/customer/main.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }
}
