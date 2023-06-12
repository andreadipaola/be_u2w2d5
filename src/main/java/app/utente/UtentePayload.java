package app.utente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtentePayload {
	@NotNull(message = "ATTENZIONE!!! Il campo Nome è obbligatorio")
	String nome;
	@NotNull(message = "ATTENZIONE!!! Il campo Cognome è obbligatorio")
	String cognome;
	@NotNull(message = "ATTENZIONE!!! Il campo email è obbligatorio")
	@Email(message = "ATTENZIONE!!! Non hai inserito un indirizzo email valido")
	String email;
	@NotNull(message = "ATTENZIONE!!! Il campo Username è obbligatorio")
	String username;
	@NotNull(message = "ATTENZIONE!!! Il campo password è obbligatorio")
//	@Size(min = 3, max = 30, message = "ATTENZIONE!!! la password deve essere minimo di 8 caratteri e massimo di 20")
	String password;
	String creditCard;
	Ruolo ruolo;
}
