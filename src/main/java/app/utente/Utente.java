package app.utente;

import java.util.Set;
import java.util.UUID;

import app.dispositivo.Dispositivo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue
	@Column(name = "id_utente")
	private UUID idUtente;
	private String username;
	private String nome;
	private String cognome;
	@OneToMany(mappedBy = "utente")
	private Set<Dispositivo> dispositivi;
}
