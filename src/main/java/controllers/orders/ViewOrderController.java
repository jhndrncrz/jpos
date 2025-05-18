package controllers.orders;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.order.Order;
import repositories.order.OrderRepository;

@WebServlet("/app/orders/view")
public class ViewOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer orderId = Integer.parseInt(request.getParameter("order_id"));
        Order order = OrderRepository.findById(orderId);

        request.setAttribute("order", order);

        request.getRequestDispatcher("/WEB-INF/app/orders/view.jsp").forward(request, response);
    }
}
