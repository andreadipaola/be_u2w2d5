package app.utente;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.AttributeConverter;

public class CreditCardConverter implements AttributeConverter<String, String> {
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
//	private static final String secret = "mysup3rs3cr3tttt";
	private static String secret;

	@Value("${spring.application.aes.secret}")
	public void setSecret(String secretKey) {
		secret = secretKey;
	}

//	@Autowired
//	PasswordEncoder bcrypt;

//	Versione 1 con encoder richiamato tramite Autowired
//	@Override
//	public String convertToDatabaseColumn(String creditCardNumber) {
//		return bcrypt.encode(creditCardNumber);
//	}

//	Versione 2 con encoder creato tramite operatore new
//	@Override
//	public String convertToDatabaseColumn(String creditCardNumber) {
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//		return bcrypt.encode(creditCardNumber);
//	}
//	Versione 3 con cifratura tramite segreto
	@Override
	public String convertToDatabaseColumn(String creditCardNumber) {

		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(ALGORITHM);

			c.init(Cipher.ENCRYPT_MODE, key);

			return Base64.getEncoder().encodeToString(c.doFinal(creditCardNumber.getBytes()));

		} catch (RuntimeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			System.out.println(e);
			throw new RuntimeException();
		}

	}

//	@Override
//	public String convertToEntityAttribute(String dbData) {
//		return null;
//	}

	@Override
	public String convertToEntityAttribute(String encryptedCreditCard) {
		Key key = new SecretKeySpec(secret.getBytes(), "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);

			return new String(c.doFinal(Base64.getDecoder().decode(encryptedCreditCard)));

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException();
		}

	}

}
