package com.example.tesi.filter;

import com.example.tesi.entity.User;
import com.example.tesi.service.UserService;
import com.example.tesi.utility.PasswordEncrypter;
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
		password= PasswordEncrypter.encrypt(password);

		User user=userService.getUserByUsername(username);

		if (user!=null && user.getPassword().equals(password)) {
			logger.log(Level.INFO, "Login successful");
			filterChain.doFilter(request, response);
		} else
			logger.log(Level.INFO, "Login failed");
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().contains("/login");
	}
}
