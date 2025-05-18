package controllers.products;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.product.Product;
import repositories.product.ProductRepository;

@WebServlet("/app/products/new")
public class NewProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/app/products/new.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> errors = new HashMap<String, String>();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer stock;
        Integer threshold;
        BigDecimal basePrice;

        try {
            stock = Integer.parseInt(request.getParameter("stock"));
        } catch (NumberFormatException e) {
            stock = 0;
            errors.put("stock", e.getMessage());
        }

        try {
            threshold = Integer.parseInt(request.getParameter("threshold"));
        } catch (NumberFormatException e) {
            threshold = 0;
            errors.put("threshold", e.getMessage());
        }

        try {
            basePrice = new BigDecimal(request.getParameter("base_price"));
        } catch (NumberFormatException e) {
            basePrice = BigDecimal.ZERO;
            errors.put("base_price", e.getMessage());
        }

        Product product = new Product();

        try {
            product.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        try {
            product.setDescription(description);
        } catch (Exception e) {
            errors.put("description", e.getMessage());
        }

        try {
            product.setStock(stock);
        } catch (Exception e) {
            errors.put("stock", e.getMessage());
        }

        try {
            product.setThreshold(threshold);
        } catch (Exception e) {
            errors.put("threshold", e.getMessage());
        }

        try {
            product.setBasePrice(basePrice);
        } catch (Exception e) {
            errors.put("base_price", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("product", product);

            request.getRequestDispatcher("/WEB-INF/app/products/new.jsp").forward(request, response);
            return;
        }

        try {
            ProductRepository.create(product);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect(request.getContextPath() + "/app/products/list");
    }
}
