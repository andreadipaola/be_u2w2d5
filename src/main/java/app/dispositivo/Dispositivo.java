package app.dispositivo;

import java.util.UUID;

import app.dispositivo.enums.StatoDispositivo;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dispositivo {
	private UUID idDispositivo;
	private String tipo;
	private StatoDispositivo statoDispositivo;
}
