package app.dispositivo;

import app.dispositivo.enums.StatoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DispositivoPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Tipo è obbligatorio")
	String tipo;
	StatoDispositivo statoDispositivo;
}
