package com.config;

import com.entity.UserActivity;
import com.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            UserActivity userActivity = (UserActivity) session.getAttribute("userActivity");
            if (userActivity != null) {
                userActivity.setLogoutTime(LocalDateTime.now());
                UserActivity savedActivity = userActivityRepository.save(userActivity);
                System.out.println("Saved UserActivity: " + savedActivity);

            }
            session.invalidate();
        }

        response.sendRedirect("/loginForm?logout");
    }
}
