package com.example.tesi.filter;

import com.example.tesi.entity.User;
import com.example.tesi.utility.PasswordEncrypter;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UserSaveFilter extends OncePerRequestFilter {
	private final Gson gson = new Gson();
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (request.getMethod().equals("POST")) {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ServletInputStream inputStream = request.getInputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = inputStream.read(buffer)) != -1)
				outputStream.write(buffer, 0, length);

			User user = gson.fromJson(outputStream+"", User.class);

			String password = user.getPassword();
			user.setPassword(PasswordEncrypter.encrypt(password));

			String modifiedJson=gson.toJson(user);

			byte[] modifiedRequestBody = modifiedJson.getBytes();

			//TODO completare costruzione body della richiesta modificata
			HttpServletRequest modifiedRequest = new HttpServletRequestWrapper(request) {
				@Override
				public ServletInputStream getInputStream() throws IOException {

				}
			}
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path= request.getServletPath();
		return !path.contains("/save");
	}
}
