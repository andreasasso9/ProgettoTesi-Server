package com.example.tesi.control;

import com.example.tesi.entity.User;
import com.example.tesi.service.UserService;
import com.example.tesi.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	}

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		user.setPassword(PasswordEncrypter.encrypt(user.getPassword()));
		userService.saveUser(user);
		logger.log(Level.INFO, "Save succcessful");
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User userToLogin=userService.getUserByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(userToLogin);
	}

	//TODO implementare gli altri metodi per user
}
