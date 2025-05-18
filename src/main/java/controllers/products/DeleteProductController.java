package controllers.products;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.product.ProductRepository;

@WebServlet("/app/products/delete")
public class DeleteProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer productId = Integer.parseInt(request.getParameter("product_id"));

        ProductRepository.delete(productId);
        
        response.sendRedirect(request.getContextPath() + "/app/products/list");
    }
}
