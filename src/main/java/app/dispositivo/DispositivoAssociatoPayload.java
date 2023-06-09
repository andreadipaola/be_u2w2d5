package app.dispositivo;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DispositivoAssociatoPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Tipo Dispositivo è obbligatorio")
	TipoDispositivo tipoDispositivo;

	@NotNull(message = "ATTENZIONE!!! Il campo Id Utente è obbligatorio")
	UUID idUtente;
}
