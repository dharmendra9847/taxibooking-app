package com.app.taxi.config;

import java.io.IOException;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response,
			@Nullable Authentication authentication) {
		
		try {
//			ServletContext servletContext = request.getServletContext();
//			servletContext.setAttribute("/logout", true);
			response.sendRedirect("/admin/dashboard");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
