package controllers.employees;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> errors = new HashMap<String, String>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String role = request.getParameter("role");
        BigDecimal salary;
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");

        try {
            salary = new BigDecimal(request.getParameter("salary"));
        } catch (NumberFormatException e) {
            salary = BigDecimal.ZERO;
            errors.put("salary", e.getMessage());
        }

        Employee employee = new Employee();

        try {
            if (EmployeeRepository.isUsernameTaken(username)) {
                throw new Exception("Username is already taken.");
            }

            employee.setUsername(username);
        } catch (Exception e) {
            errors.put("username", e.getMessage());
        }

        try {
            employee.setPasswordHash(Cryptography.hashPassword(password));
        } catch (Exception e) {
            errors.put("password", e.getMessage());
        }

        try {
            employee.setFirstName(firstName);
        } catch (Exception e) {
            errors.put("first_name", e.getMessage());
        }

        try {
            employee.setLastName(lastName);
        } catch (Exception e) {
            errors.put("last_name", e.getMessage());
        }

        try {
            employee.setRole(role);
        } catch (Exception e) {
            errors.put("role", e.getMessage());
        }

        try {
            employee.setSalary(salary);
        } catch (Exception e) {
            errors.put("salary", e.getMessage());
        }

        try {
            employee.setEmail(email);
        } catch (Exception e) {
            errors.put("email", e.getMessage());
        }

        try {
            employee.setPhoneNumber(phoneNumber);
        } catch (Exception e) {
            errors.put("phone_number", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("employee", employee);

            request.getRequestDispatcher("/WEB-INF/app/employees/new.jsp").forward(request, response);
            return;
        }

        try {
            EmployeeRepository.create(employee);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect(request.getContextPath() + "/app/employees/list");
    }
}
