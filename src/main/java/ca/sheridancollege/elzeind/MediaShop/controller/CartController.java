package ca.sheridancollege.elzeind.MediaShop.controller;

import ca.sheridancollege.elzeind.MediaShop.beans.CartItem;
import ca.sheridancollege.elzeind.MediaShop.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.List;
@Controller
public class CartController {
    @Autowired
    private DatabaseAccess db;

    @GetMapping("/secure/shopping-cart")
    public String viewShoppingCart(Model model, Principal principal) {
        String userEmail = principal.getName();
        Long userId = db.findUserIdByEmail(userEmail);

        List<CartItem> cartItems = db.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "secure/shopping-cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long itemId, @RequestParam int quantity,
                            @RequestParam String itemType, @RequestParam String redirect,
                            Principal principal, RedirectAttributes redirectAttributes) {
        Long userId = db.findUserIdByEmail(principal.getName());
        if (userId != null && itemId != null && quantity > 0 && itemType != null && !itemType.isEmpty()) {
            db.addToCart(new CartItem(userId, itemId, quantity, itemType));
            redirectAttributes.addFlashAttribute("itemAdded", true);
        } else {
            System.out.println("Invalid cart data: userId=" + userId + ", itemId=" + itemId + ", quantity=" + quantity + ", itemType=" + itemType);
        }
        return "redirect:" + redirect;
    }

    @GetMapping("/shopping-cart")
    public String viewCart(Model model, Principal principal) {
        Long userId = db.findUserIdByEmail(principal.getName());
        List<CartItem> cartItems = db.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "secure/shopping-cart";
    }

    @GetMapping("/deleteItemFromCartById/{id}")
    public String deleteItemById(Model model, @PathVariable Long id) {
        db.deleteItemFromCartById(id);
        model.addAttribute("cart", new CartItem());
        model.addAttribute("cartList", db.getCartList());
        return "secure/shopping-cart";
    }
}

