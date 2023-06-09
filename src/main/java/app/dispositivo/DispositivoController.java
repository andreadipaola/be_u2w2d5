package app.dispositivo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
	@Autowired
	private DispositivoService dispositivoService;

	// -------------------------- GET SU DISPOSITIVI -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi) OK
	@GetMapping("")
	public List<Dispositivo> getDispositivi() {
		return dispositivoService.find();
	}

	// -------------------------- POST SU DISPOSITIVI
	// --------------------------------
	// Versione 2 e payload (POST: http://localhost:3001/dispositivi) OK
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDispositivo(@RequestBody @Validated Dispositivo body) {
		return dispositivoService.create(body);
	}

	// ----------------------- GET SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi/{idDispositivo}) OK
	@GetMapping("/{dispositivoId}")
	public Dispositivo getUser(@PathVariable UUID dispositivoId) throws Exception {
		return dispositivoService.findById(dispositivoId);
	}

	// ----------------------- PUT SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (PUT: http://localhost:3001/dispositivi/{idDispositivo}) OK
	@PutMapping("/{dispositivoId}")
	public Dispositivo updateUser(@PathVariable UUID dispositivoId, @RequestBody Dispositivo body) throws Exception {
		return dispositivoService.findByIdAndUpdate(dispositivoId, body);
	}

	// -------------------- DELETE SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/dispositivi/{idDispositivo}) OK
	@DeleteMapping("/{dispositivoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID dispositivoId) throws Exception {
		dispositivoService.findByIdAndDelete(dispositivoId);
	}

}
