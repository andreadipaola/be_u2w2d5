package app.dispositivo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import app.dispositivo.enums.StatoDispositivo;

@Component
//@Order(1)
//public class DispositivoRunner implements ApplicationRunner {
public class DispositivoRunner implements CommandLineRunner {
	@Autowired
	DispositivoService dispositivoService;

	String[] stringhePredefinite = { "Laptop", "Smartphone", "Auricolari", "Tablet" };

	@Override
//	public void run(ApplicationArguments args) throws Exception {
	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 20; i++) {
			try {
				String tipo = getRandomString(stringhePredefinite);
				StatoDispositivo statoDispositivo = getRandomEnumValue(StatoDispositivo.class);
				Dispositivo dispositivo = new Dispositivo(tipo, statoDispositivo);
				dispositivoService.create(dispositivo);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		Random random = new Random();
		int index = random.nextInt(values.length);
		return values[index];
	}

	public static String getRandomString(String[] array) {
		Random random = new Random();
		int index = random.nextInt(array.length);
		return array[index];
	}

}
