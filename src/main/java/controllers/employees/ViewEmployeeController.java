package controllers.employees;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.employee.Employee;
import repositories.employee.EmployeeRepository;

@WebServlet("/app/employees/view")
public class ViewEmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer employeeId = Integer.parseInt(request.getParameter("employee_id"));
        Employee employee = EmployeeRepository.findById(employeeId);

        request.setAttribute("employee", employee);

        request.getRequestDispatcher("/WEB-INF/app/employees/view.jsp").forward(request, response);
    }
}
