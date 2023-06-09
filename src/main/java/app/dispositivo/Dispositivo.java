package app.dispositivo;

import java.util.UUID;

import app.dispositivo.enums.StatoDispositivo;
import app.utente.Utente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@NoArgsConstructor
public class Dispositivo {
	@Id
	@GeneratedValue
	@Column(name = "id_dispositivo")
	private UUID idDispositivo;
	private String tipo;
	@Column(name = "stato_dispositivo")
	private StatoDispositivo statoDispositivo;
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	public Dispositivo(String tipo, StatoDispositivo statoDispositivo) {
		super();
		this.tipo = tipo;
		this.statoDispositivo = statoDispositivo;
	}

}
