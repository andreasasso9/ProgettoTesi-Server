package com.example.tesi.interceptor;

import com.example.tesi.utility.PasswordEncrypter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UserSaveInterceptor implements HandlerInterceptor {
	private final Logger logger;

	public UserSaveInterceptor() {
		this.logger = Logger.getLogger("global");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String password = request.getParameter("password");
		password= PasswordEncrypter.encrypt(password);

		request.setAttribute("password", password);

		logger.log(Level.INFO, "Password encrypted");

		return true;
	}
}
