package ca.sheridancollege.elzeind.MediaShop.controller;
import ca.sheridancollege.elzeind.MediaShop.beans.*;
import ca.sheridancollege.elzeind.MediaShop.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private DatabaseAccess db;
    @PostMapping("/submit-order")
    public String submitOrder(Principal principal, RedirectAttributes redirectAttributes) {
        Long userId = db.findUserIdByEmail(principal.getName());
        if (userId != null && db.submitCartToOrder(userId)) {
            Order latestOrder = db.getLatestOrderForUser(userId);
            redirectAttributes.addFlashAttribute("order", latestOrder);
            return "secure/order-complete";
        } else {
            return "redirect:/shopping-cart";
        }
    }
    @GetMapping("secure/order-complete")
    public String orderConfirmation(@ModelAttribute("order") Order order, Model model) {
        if (order == null || order.getId() == null) {
            return "redirect:/shopping-cart";
        }
        List<OrderItems> orderItems = db.getOrderItemsByOrderId(order.getId());
        System.out.println("Order Items: " + orderItems);
        model.addAttribute("orderItems", orderItems);
        return "secure/order-complete";
    }

    @GetMapping("/deleteOrderById/{id}")
    public String deleteOrderById(Model model, @PathVariable Long id){
        db.deleteOrderById(id);
        model.addAttribute("order", new Order());
        model.addAttribute("orderList", db.getOrderList());
        return "admin/view-orders";
    }
    @GetMapping("/editOrderById/{id}")
    public String editMovieById(Model model, @PathVariable Long id){
        Order order = db.getOrderById(id).get(0);
        db.updateOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderList", db.getOrderList());
        return "admin/view-orders";
    }
    @PostMapping("/addOrder")
    public String insertBook(Model model, @ModelAttribute Order order){
        List<Order> existingOrders = db.getOrderById(order.getId());
        if(existingOrders.isEmpty()){
            db.insertOrder(order);
        } else {
            db.updateOrder(order);
        }
        model.addAttribute("order", new Order());
        model.addAttribute("orderList", db.getOrderList());
        return "admin/view-orders";
    }
}

