package util

import org.primefaces.PrimeFaces

object Util {
    fun executaJS(comando: String) {
        PrimeFaces.current().executeScript(comando)
    }

    fun atualizaComponente(id: String) {
        PrimeFaces.current().ajax().update(id)
    }

}
