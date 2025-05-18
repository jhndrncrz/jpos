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

@WebServlet("/app/products/edit")
public class EditProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("product_id"));
        Product product = ProductRepository.findById(productId);
        
        request.setAttribute("product", product);

        request.getRequestDispatcher("/WEB-INF/app/products/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Integer stock = Integer.parseInt(request.getParameter("stock"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        BigDecimal basePrice = new BigDecimal(request.getParameter("base_price"));

        Product product = new Product();

        Map<String, String> errors = new HashMap<String, String>();

        product.setProductId(productId);

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
            product.setLimit(limit);
        } catch (Exception e) {
            errors.put("limit", e.getMessage());
        }
        
        try {
            product.setBasePrice(basePrice);
        } catch (Exception e) {
            errors.put("base_price", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("product", product);   

            request.getRequestDispatcher("/WEB-INF/app/products/edit.jsp").forward(request, response);
            return;
        }

        ProductRepository.update(product);
        
        response.sendRedirect(request.getContextPath() + "/app/products/list");
    }
}
