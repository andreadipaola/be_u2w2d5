package app.utente;

import java.util.List;
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
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	@OneToMany(mappedBy = "utente")
	private List<Dispositivo> dispositivi;

	public Utente(String nome, String cognome, String email, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.username = username;
	}
}
