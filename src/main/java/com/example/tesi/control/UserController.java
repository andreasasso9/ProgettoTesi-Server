package com.example.tesi.control;

import com.example.tesi.entity.User;
import com.example.tesi.service.UserService;
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
	private final NotificheController notificheController;
	private final ProdottoController prodottoController;


	@Autowired
	public UserController(UserService userService, NotificheController notificheController, ProdottoController prodottoController) {
		this.userService = userService;
		logger = Logger.getLogger(this.getClass().getName());
		this.notificheController = notificheController;
		this.prodottoController = prodottoController;
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

	@PostMapping("/miPiace")
	public ResponseEntity<Boolean> miPiace(@RequestParam String sender, @RequestParam Long idProdotto) {
		if (userService.miPiace(sender.replace("\"", ""), idProdotto)) {
			notificheController.miPiace(sender, idProdotto);

			return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
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

	//TODO implementare gli altri metodi per user
}
