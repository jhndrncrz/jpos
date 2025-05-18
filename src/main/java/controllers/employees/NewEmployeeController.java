package controllers.employees;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.employee.Employee;
import repositories.employee.EmployeeRepository;
import utilities.cryptography.Cryptography;

@WebServlet("/app/employees/new")
public class NewEmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/app/employees/new.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer employeeId = Integer.parseInt(request.getParameter("employee_id"));
        String username = request.getParameter("username");
        String passwordHash = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String role = request.getParameter("role");
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");

        Employee employee = new Employee();

        employee.setEmployeeId(employeeId);
        employee.setUsername(username);
        employee.setPasswordHash(Cryptography.hashPassword(passwordHash));
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);

        EmployeeRepository.create(employee);

        response.sendRedirect(request.getContextPath() + "/app/employees/list");
    }
}
