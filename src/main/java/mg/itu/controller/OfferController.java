package mg.itu.controller;

import mg.itu.model.offer.Offer;
import mg.itu.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/all")
    public String getOffers(Model model, HttpSession session,
                            @RequestParam(required = false) String externalId) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Please log in to access the offers list");
            return "views/auth/login";
        }

        try {
            List<Offer> offers = offerService.getOffers(accessToken, externalId);
            model.addAttribute("offers", offers);
            return "views/offer/liste";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error fetching offers: " + e.getMessage());
            return "views/auth/login";
        }
    }
}