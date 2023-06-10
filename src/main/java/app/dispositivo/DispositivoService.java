package app.dispositivo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import app.dispositivo.enums.StatoDispositivo;
import app.exceptions.NotFoundException;
import app.utente.Utente;
import app.utente.UtenteService;

@Service
public class DispositivoService {

	@Autowired
	private DispositivoRepository dispositivoRepo;

	@Autowired
	UtenteService utenteService;

	// -------------------------- GET SU DISPOSITIVI -----------------------------
	// Versione 1 (GET: http://localhost:3001/dispositivi) OK
	public List<Dispositivo> find2() {
		return dispositivoRepo.findAll();
	}

	// Versione 2 con paginazione (GET: http://localhost:3001/dispositivi) OK
	public Page<Dispositivo> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return dispositivoRepo.findAll(pageable);
	}

	// --------------------- POST SU DISPOSITIVI ---------------------------
	// Versione 1 (POST: http://localhost:3001/dispositivi)
	// OK
	public Dispositivo create2(Dispositivo d) {
		return dispositivoRepo.save(d);
	}

//	versione 2 con payload
	public Dispositivo create(DispositivoPayload d) {
		StatoDispositivo statoDispositivo = StatoDispositivo.DISPONIBILE;
		Dispositivo dispositivo = new Dispositivo(d.getTipoDispositivo(), statoDispositivo);
		return dispositivoRepo.save(dispositivo);
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
//	public Dispositivo findByIdAndUpdate(UUID id, Dispositivo d) throws NotFoundException {
//		Dispositivo found = this.findById(id);
//
//		found.setIdDispositivo(id);
//		found.setTipo(d.getTipo());
//		found.setStatoDispositivo(d.getStatoDispositivo());
//
//		return dispositivoRepo.save(found);
//	}

	// Versione 2 con payload 2 (PUT:
	// http://localhost:3001/dispositivi/{idDispositivo}) OK
	public Dispositivo findByIdAndUpdate(UUID id, DispositivoAssociatoPayload d) throws NotFoundException {
		Dispositivo dispositivoFound = this.findById(id);
		Utente utenteFound = utenteService.findById(d.getIdUtente());

		dispositivoFound.setIdDispositivo(id);
//		dispositivoFound.setTipoDispositivo(d.getTipoDispositivo());
//		dispositivoFound.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
		dispositivoFound.setStatoDispositivo(d.getStatoDispositivo());
		dispositivoFound.setUtente(utenteFound);

		return dispositivoRepo.save(dispositivoFound);
	}

	// -------------------- DELETE SU SINGOLO DISPOSITIVO
	// -----------------------------
	// Versione 1 (DELETE: http://localhost:3001/dispositivi/{idDispositivo}) OK
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivo found = this.findById(id);
		dispositivoRepo.delete(found);
	}

}
