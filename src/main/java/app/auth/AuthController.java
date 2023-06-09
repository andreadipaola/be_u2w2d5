package app.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.payloads.AuthenticationSuccessfullPayload;
import app.utente.Utente;
import app.utente.UtenteLoginPayload;
import app.utente.UtentePayload;

@RestController
@RequestMapping("/auth")
public class AuthController {

//	@Autowired
//	UtenteService utenteService;
//
	// Versione 1
	@PostMapping("/register")
	public Utente register(@RequestBody UtentePayload body) {
		return new Utente();
	}

	// Versione 2
//	@PostMapping("/register")
//	public ResponseEntity<Utente> register(@RequestBody @Validated UtentePayload body) {
//		Utente createdUtente = utenteService.create3(body);
//		return new ResponseEntity<>(createdUtente, HttpStatus.CREATED);
//	}

	// Versione 1
//	@PostMapping("/login")
//	public String login(@RequestBody UtenteLoginPayload body) {
//		Utente u = new Utente("John", "Doe", "john@doe.com", "1234");
//		String token = JWTTools.createToken(u);
//		return token;
//	}

	// Versione 2
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UtenteLoginPayload body) {

		Utente u = new Utente("John", "Doe", "john@doe.com", "johndoe1", "1234");
		String token = JWTTools.createToken(u);

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

	// Versione 3
//	@PostMapping("/login")
//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UtenteLoginPayload body)
//			throws NotFoundException {
//		Utente user = utenteService.findByEmail(body.getEmail());
//		if (!body.getPassword().matches(user.getPassword()))
//			throw new UnauthorizedException("Credenziali non valide");
//		String token = JWTTools.createToken(user);
//
//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
//	}

}
