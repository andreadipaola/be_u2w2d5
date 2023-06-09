package app.utente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dispositivo.Dispositivo;
import app.exceptions.BadRequestException;
import app.exceptions.NotFoundException;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepo;

	// -------------------------- GET SU UTENTI -----------------------------
	// Versione 1 (GET: http://localhost:3001/utenti) OK
	public List<Utente> find() {
		return utenteRepo.findAll();
	}

	// -------------------------- POST SU UTENTI --------------------------------
	// Versione 3 con controllo e payload (POST: http://localhost:3001/utenti) OK
	public Utente create(UtentePayload u) {
		utenteRepo.findByEmail(u.getEmail()).ifPresent(utente -> {
			throw new BadRequestException(
					"ATTENZIONE!!! L'email con la quale stai cercando di registarti è già in uso da un altro utente");
		});
		List<Dispositivo> dispositivi = new ArrayList<>();
		Utente newUtente = new Utente(u.getNome(), u.getCognome(), u.getEmail(), u.getUsername(), u.getPassword(),
				u.getCreditCard(), dispositivi, u.getRuolo());
		return utenteRepo.save(newUtente);
	}

	// ----------------------- GET SU SINGOLO UTENTE -----------------------------
	// Versione 1 (GET: http://localhost:3001/utenti/{idUtente}) OK
	public Utente findById(UUID id) throws NotFoundException {
		return utenteRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'Utente cercato non è stato trovato!"));
	}

	// ----------------------- PUT SU SINGOLO UTENTE -----------------------------
	// Versione 1 (PUT: http://localhost:3001/utenti/{idUtente}) OK
	public Utente findByIdAndUpdate(UUID id, Utente u) throws NotFoundException {
		Utente found = this.findById(id);

		found.setIdUtente(id);
		found.setNome(u.getNome());
		found.setCognome(u.getCognome());
		found.setEmail(u.getEmail());
		found.setUsername(u.getUsername());
		found.setPassword(u.getPassword());
		found.setCreditCard(u.getCreditCard());
		found.setRuolo(u.getRuolo());

		return utenteRepo.save(found);
	}

	// -------------------- DELETE SU SINGOLO UTENTE -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/utenti/{idUtente}) OK
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		utenteRepo.delete(found);
	}

	// ------------------------ ALTRI METODI ----------------------
	public Utente findByEmail(String email) throws NotFoundException {
		return utenteRepo.findByEmail(email).orElseThrow(
				() -> new NotFoundException("ATTENZIONE!!! L'email che stai cercando non è stata trovata"));
	}

	public List<Dispositivo> findDispositiviUtente(UUID id) {
		Utente found = this.findById(id);

		return found.getDispositivi();
	}

}
