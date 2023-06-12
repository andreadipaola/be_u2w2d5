package app.utente;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import app.dispositivo.Dispositivo;

@Component
public class UtenteRunner implements CommandLineRunner {
	@Autowired
	UtenteRepository utenteRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		List<Utente> usersDB = utenteRepo.findAll();

		if (usersDB.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String nome = faker.name().firstName();
					String cognome = faker.name().lastName();
					String email = faker.internet().emailAddress();
					String username = faker.name().username();
					String password = faker.internet().password();
					String creditCard = faker.finance().creditCard();
					List<Dispositivo> dispositivi = new ArrayList<>();

					Utente utente = new Utente(nome, cognome, email, username, password, creditCard, dispositivi);
					utenteRepo.save(utente);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
