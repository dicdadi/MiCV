package dad.javafx.micv.utils;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
public class Persistencia {
	public static final File FICHEROPAISES = new File("paises.csv");
	public static final File FICHERONACIONALIDADES = new File("nacionalidades.csv");
	public static List<String> cargarPaises() throws IOException {

		if (!FICHEROPAISES.exists())
			return null;
		List<String> listadoPalabras = FileUtils.readLines(FICHEROPAISES, "UTF-8");
		return listadoPalabras;
	}
	public static List<String> cargarNacionalidades() throws IOException {

		if (!FICHERONACIONALIDADES.exists())
			return null;
		List<String> listadoPalabras = FileUtils.readLines(FICHERONACIONALIDADES, "UTF-8");
		return listadoPalabras;
	}

}
