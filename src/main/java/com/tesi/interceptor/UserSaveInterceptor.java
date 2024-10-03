package com.tesi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UserSaveInterceptor implements HandlerInterceptor {
	private final Logger logger;

	public UserSaveInterceptor() {
		this.logger = Logger.getLogger(this.getClass().getName());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String password = request.getParameter("password");
		//password= PasswordEncrypter.encrypt(password);

		request.setAttribute("password", password.replace("\"", ""));

		logger.log(Level.INFO, "Password encrypted");

		return true;
	}
}
