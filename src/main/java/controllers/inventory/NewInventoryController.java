package controllers.inventory;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.inventory.Inventory;
import repositories.inventory.InventoryRepository;


@WebServlet("/app/inventory/new")
public class NewInventoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/app/inventory/new.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                Integer itemId = Integer.parseInt(request.getParameter("itemId"));
                String itemName = request.getParameter("itemName");
                String itemType = request.getParameter("itemType");
                Float base_price = Float.parseFloat(request.getParameter("base_price"));
                Integer stock = Integer.parseInt(request.getParameter("stock"));
                Integer floorAmount = Integer.parseInt(request.getParameter("floorAmount"));
                Integer roofAmount = Integer.parseInt(request.getParameter("roofAmount"));

        Inventory inventory = new Inventory();

        inventory.setItemId(itemId);
        inventory.setItemName(itemName);
        inventory.setItemType(itemType);
        inventory.setBasePrice(base_price);
        inventory.setStock(stock);
        inventory.setTotalPrice(base_price, stock);
        inventory.setFloorAmount(floorAmount);
        inventory.setRoofAmount(roofAmount);

        InventoryRepository.create(inventory);

        response.sendRedirect(request.getContextPath() + "/app/inventory/list");
    }
}
