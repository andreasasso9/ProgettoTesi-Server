package com.tesi.control;

import com.tesi.entity.User;
import com.tesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
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
	public ResponseEntity<User> loginUser(@RequestAttribute Object user) {
		User u= (User) user;
		return ResponseEntity.ok(u);
	}

	@PostMapping("/update")
	public ResponseEntity<Boolean> updateUser(@RequestBody User user) {
		if (userService.update(user))
			return ResponseEntity.ok(true);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	}

	@PostMapping("/findUserById")
	public ResponseEntity<User> findUserById(@RequestBody UUID id) {
		User user=userService.findUserById(id);
		if (user!=null)
			return ResponseEntity.ok(user);
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/checkEmail")
	public ResponseEntity<Boolean> checkEmail(@RequestBody String email) {
		User user=userService.findByEmail(email.replace("\"", ""));
		if (user==null)
			return ResponseEntity.ok(true);
		return ResponseEntity.notFound().build();
	}
}
