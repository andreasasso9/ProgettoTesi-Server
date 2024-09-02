package com.tesi.control;

import com.tesi.entity.Token;
import com.tesi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/token")
public class TokenController {
	private final TokenService tokenService;

	@Autowired
	public TokenController(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@PostMapping("/save")
	public ResponseEntity<Boolean> save(@RequestBody Token token) {
		boolean response = tokenService.save(token);
		if (response)
			return ResponseEntity.ok(true);
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/findByUsername")
	public ResponseEntity<Set<Token>> findByUsername(@RequestBody String username) {
		Set<Token> tokens=tokenService.findByUsername(username);
		if (tokens!=null && !tokens.isEmpty())
			return ResponseEntity.ok(tokens);
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody Token token) {
		boolean response = tokenService.delete(token);
		if (response)
			return ResponseEntity.ok(true);
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/findByToken")
	public ResponseEntity<Token> findByToken(@RequestBody String token) {
		Token response = tokenService.findByToken(token);
		if (response != null)
			return ResponseEntity.ok(response);
		return ResponseEntity.notFound().build();
	}
}
