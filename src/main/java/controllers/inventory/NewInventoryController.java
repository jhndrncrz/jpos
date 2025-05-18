package controllers.inventory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

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
        Map<String, String> errors = new HashMap<String, String>();

        Integer itemId = Integer.parseInt(request.getParameter("item_id"));
        String itemName = request.getParameter("item_name");
        String itemType = request.getParameter("item_type");
        BigDecimal basePrice;
        Integer stock;
        Integer floorAmount;
        Integer roofAmount;

        try {
            basePrice = new BigDecimal(request.getParameter("base_price"));
        } catch (NumberFormatException e) {
            basePrice = BigDecimal.ZERO;
            errors.put("base_price", e.getMessage());
        }

        try {
            stock = Integer.parseInt(request.getParameter("stock"));
        } catch (NumberFormatException e) {
            stock = 0;
            errors.put("stock", e.getMessage());
        }

        try {
            floorAmount = Integer.parseInt(request.getParameter("floor_amount"));
        } catch (NumberFormatException e) {
            floorAmount = 0;
            errors.put("floor_amount", e.getMessage());
        }

        try {
            roofAmount = Integer.parseInt(request.getParameter("roof_amount"));
        } catch (NumberFormatException e) {
            roofAmount = 0;
            errors.put("roof_amount", e.getMessage());
        }

        Inventory inventory = new Inventory();

        inventory.setItemId(itemId);

        try {
            inventory.setItemName(itemName);
        } catch (Exception e) {
            errors.put("item_name", e.getMessage());
        }

        try {
            inventory.setItemType(itemType);
        } catch (Exception e) {
            errors.put("item_type", e.getMessage());
        }

        try {
            inventory.setBasePrice(basePrice);
        } catch (Exception e) {
            errors.put("base_price", e.getMessage());
        }

        try {
            inventory.setStock(stock);
        } catch (Exception e) {
            errors.put("stock", e.getMessage());
        }

        try {
            inventory.setFloorAmount(floorAmount);
        } catch (Exception e) {
            errors.put("floor_amount", e.getMessage());
        }

        try {
            inventory.setRoofAmount(roofAmount);
        } catch (Exception e) {
            errors.put("roof_amount", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("inventory", inventory);

            request.getRequestDispatcher("/WEB-INF/app/inventory/new.jsp").forward(request, response);
            return;
        }

        try {
            InventoryRepository.create(inventory);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect(request.getContextPath() + "/app/inventory/list");
    }
}
