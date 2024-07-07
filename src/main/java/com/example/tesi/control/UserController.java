package com.example.tesi.control;

import com.example.tesi.entity.Notifica;
import com.example.tesi.entity.Prodotto;
import com.example.tesi.entity.User;
import com.example.tesi.service.NotificheService;
import com.example.tesi.service.ProdottoService;
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


	@Autowired
	public UserController(UserService userService, NotificheController notificheController) {
		this.userService = userService;
		logger = Logger.getLogger(this.getClass().getName());
		this.notificheController = notificheController;
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

	@PostMapping("/miPiace")
	public ResponseEntity<Boolean> miPiace(@RequestParam UUID idUser, @RequestParam Long idProdotto) {
		if (userService.miPiace(idUser, idProdotto)) {
			notificheController.miPiace(idUser, idProdotto);

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
