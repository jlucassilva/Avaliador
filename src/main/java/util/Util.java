package util;

import org.primefaces.context.RequestContext;

public class Util {
    public static void executaJS(String comando) {
        RequestContext.getCurrentInstance().execute(comando);
    }

}
