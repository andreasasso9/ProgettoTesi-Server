package com.tesi.filter;

import com.tesi.entity.User;
import com.tesi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UserLoginFilter extends OncePerRequestFilter {

	private final UserService userService;
	private final Logger logger;

	@Autowired
	public UserLoginFilter(UserService userService) {
		this.userService = userService;
		logger=Logger.getLogger(this.getClass().getName());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//password= PasswordEncrypter.encrypt(password);

		User user=userService.getUserByUsername(username);

		if (user!=null && user.getPassword().equals(password.replace("\"", ""))) {
			logger.log(Level.INFO, "Login successful");
			request.setAttribute("user", user);
			filterChain.doFilter(request, response);
		} else
			logger.log(Level.INFO, "Login failed");
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().contains("/login");
	}
}
