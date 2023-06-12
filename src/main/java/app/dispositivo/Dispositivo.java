package app.dispositivo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import app.dispositivo.enums.StatoDispositivo;
import app.utente.Utente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_dispositivo")
	private TipoDispositivo tipoDispositivo;
	@Enumerated(EnumType.STRING)
	@Column(name = "stato_dispositivo")
	private StatoDispositivo statoDispositivo = StatoDispositivo.DISPONIBILE;;
	@ManyToOne
	@JoinColumn(name = "id_utente")
	@JsonBackReference
	private Utente utente;

	public Dispositivo(TipoDispositivo tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public Dispositivo(StatoDispositivo statoDispositivo) {
		this.statoDispositivo = statoDispositivo;
	}

//	public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo) {
//		this.statoDispositivo = statoDispositivo;
//	}

}
