package app.dispositivo;

import java.util.UUID;

import app.dispositivo.enums.StatoDispositivo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DispositivoAssociatoPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Stato Dispositivo è obbligatorio")
	StatoDispositivo statoDispositivo;
//	@NotNull(message = "ATTENZIONE!!! Il campo Stato Dispositivo è obbligatorio")
//	StatoDispositivo statoDispositivo;

//	@NotNull(message = "ATTENZIONE!!! Il campo Id Utente è obbligatorio")
	UUID idUtente;
}
