package mg.itu.controller;

import mg.itu.model.invoice.Invoice;
import mg.itu.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
 
import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/all")
    public String getInvoices(Model model, HttpSession session,
                              @RequestParam(required = false) String externalId) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to access the invoices list");
            return "views/auth/login";
        }

        try {
            List<Invoice> invoices = invoiceService.getInvoices(accessToken, externalId);
            model.addAttribute("invoices", invoices);
            if (externalId != null) {
                Double amountDue = invoiceService.getAmountDue(accessToken, externalId);
                model.addAttribute("amountDue", amountDue);
            }
            return "views/invoice/liste";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error fetching invoices: " + e.getMessage());
            return "views/auth/login";
        }
    }
}