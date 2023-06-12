package app.utente;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import app.dispositivo.Dispositivo;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@JsonIgnoreProperties({ "password", "creditCard", "role", "enabled", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired", "authorities" })
public class Utente implements UserDetails {
	@Id
	@GeneratedValue
	@Column(name = "id_utente")
	private UUID idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String username;
	private String password;
	@Convert(converter = CreditCardConverter.class)
	private String creditCard;
	@OneToMany(mappedBy = "utente")
	@JsonManagedReference
	private List<Dispositivo> dispositivi;
	@Enumerated(EnumType.STRING)
	private Role role;

	public Utente(String nome, String cognome, String email, String username, String password, String creditCard,
			List<Dispositivo> dispositivi) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.username = username;
		this.password = password;
		this.creditCard = creditCard;
		this.dispositivi = dispositivi;
		this.role = Role.USER;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

//	@Override
//	public String getUsername() {
//		return this.email;
//	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
