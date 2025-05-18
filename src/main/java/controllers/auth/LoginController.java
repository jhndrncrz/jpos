package controllers.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.employee.Employee;
import repositories.employee.EmployeeRepository;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.getSession().setAttribute("error", "Username and password are required.");
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        Employee employee = EmployeeRepository.authenticate(username, password);

        if (employee != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("employeeId", employee.getEmployeeId());
            session.setAttribute("username", employee.getUsername());
            session.setAttribute("role", employee.getRole());
            session.setAttribute("fullName", employee.getFirstName() + " " + employee.getLastName());
            response.sendRedirect(request.getContextPath() + "/app/dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(request, response);
            return;
        }
    }
}
