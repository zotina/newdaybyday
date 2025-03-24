package mg.itu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import mg.itu.model.form.LoginRequest;
import mg.itu.model.form.LoginResponse;
import mg.itu.service.AuthService;

@Controller 
@RequestMapping("/api/auth")
public class AuthController {
 
    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String index() {
        return "views/auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        LoginRequest loginRequest = new LoginRequest(email, password);
 
        try { 
            LoginResponse loginResponse = authService.login(loginRequest);
            if (loginResponse != null) {
                session.setAttribute("access_token", loginResponse.getAccessToken());
                session.setAttribute("user", email); 
                return "redirect:/api/dashboard";  
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "views/auth/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Invalid email or password");
            return "views/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String accessToken = (String) session.getAttribute("access_token");

        if (accessToken != null) {
            try {
                authService.logout(accessToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        session.invalidate();
        return "redirect:/api/auth/";
    }
}