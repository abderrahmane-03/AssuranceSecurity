package com.config;

import com.entity.CustomUserDetails;
import com.entity.UserActivity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Component
public class UserActivityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        UserActivity userActivity = (UserActivity) session.getAttribute("userActivity");

        if (userActivity == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long userId = null;

            if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
                userId = ((CustomUserDetails) authentication.getPrincipal()).getId(); // Assuming `CustomUserDetails` exposes `getId()`
            }

            if (userId != null) {
                userActivity = new UserActivity(userId, LocalDateTime.now());
                session.setAttribute("userActivity", userActivity);
            }
        }

        // Update actions
        String action = determineActionFromRequest(request);
        if (action != null) {
            userActivity.addAction(action);
        }
        System.out.println("UserActivity added to session: " + userActivity);

        return true;
    }

    private String determineActionFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri.contains("/insurances/RequestedHomeInsurance")){
            return "Created insurance";
        }
        if (uri.contains("/insurances/RequestedCarInsurance")){
                return "Created insurance";
        }
        if (uri.contains("/insurances/RequestedHealthInsurance")){
                    return "Created insurance";
        }
        else if (uri.contains("/devis/approve")){
            return "approved a devis";
        } else if (uri.contains("/devis/reject")){
            return "canceld a devis";
        } else if (uri.contains("/subscription")){
            return "got into subscription";
        }
        return "Default action for URI: " + uri;

    }

}
