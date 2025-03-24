package mg.itu.controller;

import mg.itu.model.payment.PaymentMonth;
import mg.itu.service.ChartService;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
@Controller
@RequestMapping("/chart")
public class ChartController {

    private static final Logger logger = LoggerFactory.getLogger(ChartController.class);

    @Autowired
    private ChartService chartService;

    @GetMapping("/client-overview")
    public String getClientOverview(Model model, HttpSession session,
                                    @RequestParam(required = false) String externalId) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to view client overview");
            return "views/auth/login";
        }
 
        try {
            model.addAttribute("clientData", chartService.getClientOverview(accessToken, externalId));
            return "views/chart/client_overview";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching client overview: " + e.getMessage());
            return "views/chart/client_overview";
        }
    }

    @GetMapping("/task-status-summary")
    public String getStatusSummary(Model model, HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to view status summary");
            return "views/auth/login";
        }

        try {
            model.addAttribute("taskStatuses", chartService.getTaskStatusSummary(accessToken));
            model.addAttribute("invoiceStatuses", chartService.getInvoiceStatusSummary(accessToken));
            return "views/chart/task_status_summary";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching status summary: " + e.getMessage());
            return "views/chart/task_status_summary";
        }
    }

    @GetMapping("/payment-summary")
    public String getPaymentSummary(Model model, HttpSession session,
                                    @RequestParam(required = false) String month) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to view payment summary");
            return "views/auth/login";
        }

        try {
            model.addAttribute("paymentData", chartService.getPaymentSummary(accessToken, month));
            return "views/chart/payment_summary";
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching payment summary: " + e.getMessage());
            return "views/chart/payment_summary";
        }
    } 
    @GetMapping("/payment-summary-by-month")
    public String getPaymentSummaryByMonth(Model model, HttpSession session,
                                           @RequestParam(required = false) String year) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to view payment summary by month");
            return "views/auth/login";
        }  
  
        try {
            List<PaymentMonth> paymentMonths = chartService.getPaymentSummaryByMonth(accessToken, year);
            logger.info("Payment Months from service: " + paymentMonths); 
            model.addAttribute("paymentMonths", paymentMonths);
            model.addAttribute("year", year != null ? year : String.valueOf(java.time.Year.now().getValue()));

            double totalAmount = paymentMonths != null ? 
                paymentMonths.stream()
                            .map(pm -> pm.getAmountTotal() != null ? pm.getAmountTotal() : 0.0)
                            .reduce(0.0, Double::sum) : 0.0;
            model.addAttribute("totalAmount", totalAmount);
            logger.info("Total Amount: " + totalAmount); 

            return "views/chart/payment_summary_by_month";
        } catch (Exception e) {
            return "views/auth/login";
        }
    }
}