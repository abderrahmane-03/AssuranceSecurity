package com.controller;

import com.entity.Client;
import com.enums.RoleEnum;
import com.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private HttpSession session;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registered")
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String password) {


        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        client.setRole(RoleEnum.ROLE_USER);
        client.setPassword(passwordEncoder.encode(password));
        clientService.save(client);

        // Redirect to login with success message (optional)
        return new ModelAndView("redirect:/loginForm");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String email,
                              @RequestParam String password,
                              HttpServletRequest request) {
        try {
            UserDetails userDetails = clientService.loadUserByUsername(email);

            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                // Set authentication in SecurityContext
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Start session manually
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

                return new ModelAndView("redirect:/");
            }

            return new ModelAndView("loginForm", "error", "Invalid password");
        } catch (UsernameNotFoundException e) {
            return new ModelAndView("loginForm", "error", "User not found");
        }
    }
    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get the existing session, if any
        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/loginForm?logoutSuccess");
    }

}
