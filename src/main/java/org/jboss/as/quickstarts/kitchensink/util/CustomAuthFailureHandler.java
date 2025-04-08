package org.jboss.as.quickstarts.kitchensink.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.as.quickstarts.kitchensink.exceptions.UserNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException, ServletException {
        if (exception.getCause() instanceof UserNotFoundException) {
            // Redirect to register page if user doesn't exist
            getRedirectStrategy().sendRedirect(request, response, "/register");
        } else {
            getRedirectStrategy().sendRedirect(request, response, "/login?error=true");
        }
    }
}

