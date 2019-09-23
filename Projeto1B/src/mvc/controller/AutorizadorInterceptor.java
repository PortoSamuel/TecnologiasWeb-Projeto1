package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.endsWith("formulario_login") || uri.endsWith("registro") || uri.endsWith("adiciona")
				|| uri.endsWith("efetuaLogin") || uri.endsWith("deletaNota") || uri.endsWith("editaNota")
				|| uri.endsWith("paginaUsuario") || uri.endsWith("criaNota") || uri.endsWith("editarNota")
				|| uri.endsWith("adicionarNota")) {
			return true;
		}
		if (request.getSession().getAttribute("usuarioLogado") != null
				|| request.getSession().getAttribute("userLogin") != null
				|| request.getSession().getAttribute("noteId") != null) {
			return true;
		}
		response.sendRedirect("formulario_login");
		return false;
	}
}