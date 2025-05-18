package controllers.employees;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.employee.EmployeeRepository;

@WebServlet("/app/employees/delete")
public class DeleteEmployeeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer employeeId = Integer.parseInt(request.getParameter("employee_id"));

        EmployeeRepository.delete(employeeId);
        
        response.sendRedirect(request.getContextPath() + "/app/employees/list");
    }
}
