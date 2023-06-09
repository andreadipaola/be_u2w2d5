package app.dispositivo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DispositivoPayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Tipo è obbligatorio")
	TipoDispositivo tipoDispositivo;
}
