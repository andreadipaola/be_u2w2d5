package app.utente;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.dispositivo.Dispositivo;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	@Autowired
	private UtenteService utenteService;

	// -------------------------- GET SU UTENTI -----------------------------
	// Versione 1 (GET: http://localhost:3001/utenti) OK
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<Utente> getUtenti() {
		return utenteService.find();
	}

	// -------------------------- POST SU UTENTI --------------------------------
	// Versione 2 e payload (POST: http://localhost:3001/utenti) OK
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser2(@RequestBody @Validated UtentePayload body) {
		return utenteService.create(body);
	}

	// ----------------------- GET SU SINGOLO UTENTE -----------------------------
	// Versione 1 (GET: http://localhost:3001/utenti/{idUtente}) OK
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{idUtente}")
	public Utente getUser(@PathVariable UUID idUtente) throws Exception {
		return utenteService.findById(idUtente);
	}

	// ------------ GET PER OTTENERE I DISPOSITIVI DEL SINGOLO UTENTE -------------
	// Versione 1 (GET: http://localhost:3001/utenti/{idUtente}/dispositivi) OK
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{idUtente}/dispositivi")
	public List<Dispositivo> findDispositiviUtente(@PathVariable UUID idUtente) {
		return utenteService.findDispositiviUtente(idUtente);
	}

	// ----------------------- PUT SU SINGOLO UTENTE -----------------------------
	// Versione 1 (PUT: http://localhost:3001/utenti/{idUtente}) OK
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{idUtente}")
	public Utente updateUser(@PathVariable UUID idUtente, @RequestBody Utente body) throws Exception {
		return utenteService.findByIdAndUpdate(idUtente, body);
	}

	// -------------------- DELETE SU SINGOLO UTENTE -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/utenti/{idUtente}) OK
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{idUtente}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID idUtente) throws Exception {
		utenteService.findByIdAndDelete(idUtente);
	}

}
