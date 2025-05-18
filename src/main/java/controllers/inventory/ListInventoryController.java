package controllers.inventory;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.inventory.Inventory;
import repositories.inventory.InventoryRepository;

@WebServlet("/app/inventory/list")
public class ListInventoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Inventory> inventories = InventoryRepository.findAll();
        request.setAttribute("inventories", inventories);
        
        request.getRequestDispatcher("/WEB-INF/app/inventory/list.jsp").forward(request, response);
    }
}