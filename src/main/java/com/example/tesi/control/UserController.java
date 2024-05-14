package com.example.tesi.control;

import com.example.tesi.entity.User;
import com.example.tesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	//TODO implementare gli altri metodi per user
}
