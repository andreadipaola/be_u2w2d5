package app.utente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UtenteLoginPayload {
	@Email(message = "ATTENZIONE!!! Non hai inserito un indirizzo email valido")
	String email;
	@NotNull(message = "ATTENZIONE!!! Il campo password è obbligatorio")
	@Size(min = 3, max = 30, message = "ATTENZIONE!!! la password deve essere minimo di 8 caratteri e massimo di 20")
	String password;
}
