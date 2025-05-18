package controllers.inventory;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.inventory.Inventory;
import repositories.inventory.InventoryRepository;

@WebServlet("/app/inventory/view")
public class ViewInventoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer itemId = Integer.parseInt(request.getParameter("itemId"));
        Inventory inventory = InventoryRepository.findById(itemId);

        request.setAttribute("inventory", inventory);

        request.getRequestDispatcher(request.getContextPath() + "/app/inventory/view.jsp").forward(request, response);
    }
}
