package app.dispositivo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.exceptions.NotFoundException;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
	@Autowired
	private DispositivoService dispositivoService;

	// -------------------------- GET SU DISPOSITIVI -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi) OK
//	@GetMapping("")
//	public List<Dispositivo> getDispositivi() {
//		return dispositivoService.find();
//	}

	// Versione 2 con paginazione (GET: http://localhost:3001/dispositivi) OK
	@GetMapping("")
	public Page<Dispositivo> getDispositivi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "idDispositivo") String sortBy) {
		return dispositivoService.find(page, size, sortBy);
	}

	// -------------------------- POST SU DISPOSITIVI
	// --------------------------------
	// Versione 2 e payload e validazione (POST: http://localhost:3001/dispositivi)
	// OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoPayload body) {
		return dispositivoService.create(body);
	}

	// ----------------------- GET SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi/{idDispositivo}) OK
	@GetMapping("/{idDispositivo}")
	public Dispositivo getDispositivo(@PathVariable UUID idDispositivo) throws Exception {
		return dispositivoService.findById(idDispositivo);
	}

	// ----------------------- PUT SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (PUT: http://localhost:3001/dispositivi/{idDispositivo}) OK
//	@PutMapping("/{dispositivoId}")
//	public Dispositivo updateDispositivo(@PathVariable UUID dispositivoId, @RequestBody Dispositivo body) throws Exception {
//		return dispositivoService.findByIdAndUpdate(dispositivoId, body);
//	}

	// Versione 2 (PUT: http://localhost:3001/dispositivi/{idDispositivo}) OK
	@PutMapping("/{idDispositivo}")
	public Dispositivo updateDispositivo(@PathVariable UUID idDispositivo,
			@RequestBody DispositivoAssociatoPayload body) throws Exception {
		return dispositivoService.findByIdAndUpdate(idDispositivo, body);
	}

	// -------------------- DELETE SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/dispositivi/{idDispositivo}) OK
	@DeleteMapping("/{idDispositivo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID idDispositivo) throws NotFoundException {
		dispositivoService.findByIdAndDelete(idDispositivo);
	}

}
