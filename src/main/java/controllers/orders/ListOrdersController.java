package controllers.orders;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.order.Order;
import repositories.order.OrderRepository;

@WebServlet("/app/orders/list")
public class ListOrdersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> orders = OrderRepository.findAll();
        request.setAttribute("orders", orders);
        
        request.getRequestDispatcher("/WEB-INF/app/orders/list.jsp").forward(request, response);
    }
}