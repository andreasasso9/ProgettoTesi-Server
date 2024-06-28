package com.example.tesi.control;

import com.example.tesi.entity.User;
import com.example.tesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	private final Logger logger;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
		logger = Logger.getLogger(this.getClass().getName());
	}

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(
			@RequestParam String email,
			@RequestParam String username,
			@RequestAttribute String password,
			@RequestParam String indirizzo
	) {
		User user=new User(email, username, password, indirizzo);
		userService.saveUser(user);

		logger.log(Level.INFO, "Save succcessful");

		return ResponseEntity.ok(user);
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User userToLogin=userService.getUserByUsername(username);
		return ResponseEntity.ok(userToLogin);
	}

	//TODO implementare gli altri metodi per user
}
