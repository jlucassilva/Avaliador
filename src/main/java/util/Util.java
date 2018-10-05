package util;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

public class Util {
	public static void executaJS(String comando) {
		PrimeFaces.current().executeScript(comando);
	}

	public static void atualizaComponente(String id) {
		PrimeFaces.current().ajax().update(id);
	}

}
