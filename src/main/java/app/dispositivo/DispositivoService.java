package app.dispositivo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.exceptions.NotFoundException;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository dispositivoRepo;

	// -------------------------- GET SU DISPOSITIVI -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi) OK
	public List<Dispositivo> find() {
		return dispositivoRepo.findAll();
	}

	// --------------------- POST SU DISPOSITIVI ---------------------------
	// Versione 3 con controllo e payload (POST: http://localhost:3001/dispositivi)
	// OK
	public Dispositivo create(Dispositivo d) {
		return dispositivoRepo.save(d);
	}

	// ----------------------- GET SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi/{idDispositivo}) OK
	public Dispositivo findById(UUID id) throws NotFoundException {
		return dispositivoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("ATTENZIONE!!! L'Dispositivo cercato non è stato trovato!"));
	}

	// ----------------------- PUT SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (PUT: http://localhost:3001/dispositivi/{idDispositivo}) OK
	public Dispositivo findByIdAndUpdate(UUID id, Dispositivo d) throws NotFoundException {
		Dispositivo found = this.findById(id);

		found.setIdDispositivo(id);
		found.setTipo(d.getTipo());
		found.setStatoDispositivo(d.getStatoDispositivo());

		return dispositivoRepo.save(found);
	}

	// -------------------- DELETE SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/dispositivi/{idDispositivo}) OK
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivo found = this.findById(id);
		dispositivoRepo.delete(found);
	}

}
