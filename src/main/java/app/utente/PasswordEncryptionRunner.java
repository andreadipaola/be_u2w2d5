package app.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//@Order(2)
public class PasswordEncryptionRunner implements CommandLineRunner {
	@Autowired
	private UtenteRepository utenteRepo;

	@Autowired
	private PasswordEncoder bcrypt;

	@Override
	public void run(String... args) {
		encryptPasswords();
	}

	private void encryptPasswords() {
		// Recupera gli utenti dal database
		List<Utente> utenti = utenteRepo.findAll();

		// Cifra le password per ogni utente
		for (Utente utente : utenti) {
			String plainPW = utente.getPassword();
			String hashedPW = bcrypt.encode(plainPW);
			utente.setPassword(hashedPW);
		}

		// Salva gli utenti con le password cifrate nel database
		utenteRepo.saveAll(utenti);
	}
}
