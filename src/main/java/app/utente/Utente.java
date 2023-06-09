package app.utente;

import java.util.Set;
import java.util.UUID;

import app.dispositivo.Dispositivo;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	private UUID idUtente;
	private String username;
	private String nome;
	private String cognome;
	private Set<Dispositivo> dispositivi;
}
