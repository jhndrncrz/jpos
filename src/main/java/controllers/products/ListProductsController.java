package controllers.products;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.product.Product;
import repositories.product.ProductRepository;

@WebServlet("/app/products/list")
public class ListProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = ProductRepository.findAll();
        request.setAttribute("products", products);
        
        request.getRequestDispatcher("/WEB-INF/app/products/list.jsp").forward(request, response);
    }
}