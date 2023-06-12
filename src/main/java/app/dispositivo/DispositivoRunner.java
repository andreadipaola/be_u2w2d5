package app.dispositivo;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//@Order(3)
//public class DispositivoRunner implements ApplicationRunner {
public class DispositivoRunner implements CommandLineRunner {
	@Autowired
	DispositivoService dispositivoService;

//	String[] stringhePredefinite = { "Laptop", "Smartphone", "Auricolari", "Tablet" };

	@Override
//	public void run(ApplicationArguments args) throws Exception {
	public void run(String... args) throws Exception {
		List<Dispositivo> dispositivi = dispositivoService.find2();
		if (dispositivi.size() == 0) {
//		Faker faker = new Faker(new Locale("it"));
			for (int i = 0; i < 20; i++) {
				try {
//				String tipo = getRandomString(stringhePredefinite);
					TipoDispositivo tipoDispositivo = getRandomEnumValue(TipoDispositivo.class);
					Dispositivo dispositivo = new Dispositivo(tipoDispositivo);
					dispositivoService.create2(dispositivo);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

	public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		Random random = new Random();
		int index = random.nextInt(values.length);
		return values[index];
	}

//	public static String getRandomString(String[] array) {
//		Random random = new Random();
//		int index = random.nextInt(array.length);
//		return array[index];
//	}

}
