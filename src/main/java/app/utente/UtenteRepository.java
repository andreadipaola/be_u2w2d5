package app.utente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
	// La versione 1 non aveva questo metodo perché non c'era un controllo nel
	// metodo create del service
	Optional<Utente> findByEmail(String email);
}
