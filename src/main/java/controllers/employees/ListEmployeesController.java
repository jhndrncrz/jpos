package controllers.employees;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.employee.Employee;
import repositories.employee.EmployeeRepository;

@WebServlet("/app/employees/list")
public class ListEmployeesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = EmployeeRepository.findAll();
        request.setAttribute("employees", employees);
        
        request.getRequestDispatcher("/WEB-INF/app/employees/list.jsp").forward(request, response);
    }
}