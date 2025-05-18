package controllers.inventory;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.employee.EmployeeRepository;

@WebServlet("/app/inventory/delete")
public class DeleteInventoryController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer itemId = Integer.parseInt(request.getParameter("itemId"));

        EmployeeRepository.delete(itemId);
        
        response.sendRedirect(request.getContextPath() + "/app/inventory/list");
    }
}
