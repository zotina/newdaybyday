package mg.itu.controller;

import mg.itu.model.client.Client;
import mg.itu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession; 
  
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public String getClients(Model model, HttpSession session, 
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int perPage) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken == null) {
            model.addAttribute("error", "Veuillez vous connecter pour voir la liste des clients");
            return "views/auth/login";
        }

        try {
            List<Client> clients = clientService.getClients(accessToken, page, perPage);
            
            model.addAttribute("clients", clients);
            model.addAttribute("currentPage", page);
            model.addAttribute("perPage", perPage);
            model.addAttribute("nextPage", page + 1);
            model.addAttribute("prevPage", (page > 1) ? page - 1 : 1);

            return "views/clients/liste";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la récupération des clients : " + e.getMessage());
            return "views/auth/login";
        }
    }

}