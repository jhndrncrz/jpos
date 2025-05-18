package controllers.products;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.product.Product;
import repositories.product.ProductRepository;

@WebServlet("/app/products/view")
public class ViewProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("product_id"));
        Product product = ProductRepository.findById(productId);

        request.setAttribute("product", product);

        request.getRequestDispatcher("/WEB-INF/app/products/view.jsp").forward(request, response);
    }
}
