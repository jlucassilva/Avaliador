package util;

 import kontroller.Usuario;
 import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("*.xhtml")
public class AutorizacaoFilter extends HttpFilter {
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws ServletException, IOException {

		if (session != null && session.getAttribute("usuarioLogado") != null) {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			if (request.getRequestURI().endsWith("/login.xhtml"))
				chain.doFilter(request, response);
			else if (request.getRequestURI().endsWith("/principal.xhtml") && usuario.getCandidato() == null)
				Servlets.facesRedirect(request, response, "vaga.xhtml");
			else if (request.getRequestURI().endsWith("/vaga.xhtml") && usuario.getAnunciante() == null)
				Servlets.facesRedirect(request, response, "principal.xhtml");
			else
				chain.doFilter(request, response);

		} else {
			if (request.getRequestURI().endsWith("/login.xhtml"))
				chain.doFilter(request, response);
			else if (request.getRequestURI().endsWith("/usuario.xhtml"))
				chain.doFilter(request, response);
			else
				response.sendRedirect(request.getContextPath() + "login.xhtml");
		}
	}
}
