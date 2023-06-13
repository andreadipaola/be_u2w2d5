package app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import app.exceptions.NotFoundException;
import app.exceptions.UnauthorizedException;
import app.payloads.AuthenticationSuccessfullPayload;
import app.utente.Utente;
import app.utente.UtenteLoginPayload;
import app.utente.UtentePayload;
import app.utente.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService utenteService;

	@Autowired
	private PasswordEncoder bcrypt;

	// Versione 1
//	@PostMapping("/register")
//	public Utente register(@RequestBody UtentePayload body) {
//		return new Utente();
//	}

	// Versione 2 con ResponseEntity e validazione
//	@PostMapping("/register")
//	public ResponseEntity<Utente> register(@RequestBody @Validated UtentePayload body) {
//		Utente createdUtente = utenteService.create(body);
//		return new ResponseEntity<>(createdUtente, HttpStatus.CREATED);
//	}

	// Versione 3 con ResponseEntity e validazione e BCrypt
//	@PostMapping("/register")
//	public ResponseEntity<Utente> register(@RequestBody @Validated UtentePayload body) {
//
//		body.setPassword(bcrypt.encode(body.getPassword()));
//		Utente createdUtente = utenteService.create(body);
//		return new ResponseEntity<>(createdUtente, HttpStatus.CREATED);
//	}
//	
	// Versione 4 con ResponseEntity e validazione e BCrypt e invio email di
	// conferma
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUtente(@RequestBody @Validated UtentePayload body) {
		RestTemplate restTemplate = new RestTemplate();

		body.setPassword(bcrypt.encode(body.getPassword()));
		Utente createdUtente = utenteService.create(body);

		HttpEntity<Utente> requestBody = new HttpEntity<Utente>(createdUtente);
		restTemplate.postForObject("http://localhost:3002/auth/register", requestBody, Utente.class);

		return createdUtente;

	}
	// Versione 1
//	@PostMapping("/login")
//	public String login(@RequestBody UtenteLoginPayload body) {
//		Utente u = new Utente("John", "Doe", "john@doe.com", "1234");
//		String token = JWTTools.createToken(u);
//		return token;
//	}

	// Versione 3 con ResponseEntity, personalizzazione del token e validazione
//	@PostMapping("/login")
//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UtenteLoginPayload body) {
//
//		Utente u = new Utente("John", "Doe", "john@doe.com", "johndoe1", "1234");
//		String token = JWTTools.createToken(u);
//
//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
//	}

	// Versione 3 con ResponseEntity, personalizzazione del token, validazione e
	// controllo della password
//	@PostMapping("/login")
//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UtenteLoginPayload body)
//			throws NotFoundException {
//		Utente utente = utenteService.findByEmail(body.getEmail());
//		if (!body.getPassword().matches(utente.getPassword()))
//			throw new UnauthorizedException("Credenziali non valide");
//		String token = JWTTools.createToken(utente);
//
//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
//	}

	// Versione 4 con ResponseEntity, personalizzazione del token, validazione,
	// controllo della password con BCrypt
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody @Validated UtenteLoginPayload body)
			throws NotFoundException {
		Utente utente = utenteService.findByEmail(body.getEmail());

		String plainPW = body.getPassword();
		String hashedPW = utente.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");
		String token = JWTTools.createToken(utente);

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
