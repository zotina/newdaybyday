package mg.itu.controller;

import mg.itu.model.payment.Payment;
import mg.itu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    public String getPayments(Model model, HttpSession session,
                              @RequestParam(required = false) String externalId) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to access the payments list");
            return "views/auth/login";
        }

        try {
            List<Payment> payments = paymentService.getPayments(accessToken, externalId);
            model.addAttribute("payments", payments);
            return "views/payment/liste";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching payments: " + e.getMessage());
            return "views/auth/login";
        }
    }

    @PostMapping("/delete")
    public String deletePayment(Model model, HttpSession session,
                                @RequestParam("externalId") String externalId) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to delete a payment");
            return "views/auth/login";
        }

        try {
            paymentService.deletePayment(accessToken, externalId);
            return "redirect:/payment/all";
        } catch (Exception e) {
            model.addAttribute("error", "Error deleting payment: " + e.getMessage());
            return "views/payment/liste";
        }
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model, HttpSession session,
                                 @RequestParam("externalId") String externalId) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to update a payment");
            return "views/auth/login";
        }

        try {
            List<Payment> payments = paymentService.getPayments(accessToken, externalId);
            if (payments.isEmpty()) {
                model.addAttribute("error", "Payment not found");
                return "views/payment/liste";
            }
            model.addAttribute("payment", payments.get(0));
            return "views/payment/payment_update";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching payment: " + e.getMessage());
            return "views/payment/liste";
        }
    }

    @PostMapping("/update")
    public String updatePayment(Model model, HttpSession session,
                                @RequestParam("externalId") String externalId,
                                @RequestParam("amount") Double amount) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to update a payment");
            return "views/auth/login";
        }

        try {
            paymentService.updatePayment(accessToken, externalId, amount);
            return "redirect:/payment/all";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Amount paid exceeds the invoice total due");
            return "views/payment/payment_update";
        }
    }
}