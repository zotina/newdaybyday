package mg.itu.controller;

import mg.itu.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/settings")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/update-discount")
    public String showUpdateDiscountForm(Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");
        if (accessToken == null) {
            model.addAttribute("error", "Please log in to update discount");
            return "views/auth/login";
        }
        return "views/settings/update_discount";
    }

    @PostMapping("/update-discount")
    public String updateDiscount(@RequestParam("remise") Double remise, Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");
        if (accessToken == null) {
            model.addAttribute("error", "Please log in to update discount");
            return "views/auth/login";
        }

        try {
            settingService.updateDiscount(accessToken, remise);
            model.addAttribute("success", "Discount updated successfully");
        } catch (Exception e) {
            model.addAttribute("error", "Error updating discount: " + e.getMessage());
        }
        return "views/settings/update_discount";
    }
}