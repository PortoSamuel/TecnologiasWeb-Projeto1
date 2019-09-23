package mvc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.DAO;
import mvc.model.Note;
import mvc.model.Pessoa;

@Controller
public class CRUDController {

	@RequestMapping("formulario_login")
	public String execute() {
		return "first_page";
	}

	@RequestMapping("registro")
	public String registro() {
		return "cadastro";
	}

	@RequestMapping(value = "adiciona", method = RequestMethod.POST)
	public String upload(Pessoa pessoa) throws IOException {
		DAO dao = new DAO();

		Pessoa verifica = dao.verificaLogin(pessoa.getLogin());

		if (verifica.getLogin() != null) {
			return "usuario_invalido";
		} else {
			if (pessoa.getSenha().equals(pessoa.getConfirma_senha())) {
				dao.adicionaPessoa(pessoa);
				return "redirect:formulario_login";

			} else {
				return "senhas_incorretas";
			}
		}

	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Pessoa pessoa, HttpSession session, HttpServletRequest request) {
		DAO dao = new DAO();

		Pessoa usuario = new Pessoa();

		Boolean valor = (Boolean) request.getAttribute("valor");

		String classifica = request.getParameter("classifica");
		String filtra = request.getParameter("filtra_materia");

		request.setAttribute("classifica", classifica);
		request.setAttribute("filtra", filtra);

		if (classifica != null || filtra != null) {

			valor = true;
		}

		if (valor != null) {
			String login = (String) request.getAttribute("login");

			if (login == null) {
				login = pessoa.getNome();
			}

			usuario = dao.logIn(login);

		} else {
			usuario = dao.logIn(pessoa.getLogin());
		}

		if (usuario.getLogin() == null) {
			return "usuario_invalido";

		} else {
			if (!usuario.getSenha().equals(pessoa.getSenha()) && valor == null) {
				return "senhas_incorretas";
			} else {
				session.setAttribute("usuarioLogado", usuario.getLogin());

				return "user_page";
			}
		}
	}

	@RequestMapping(value = "deletaNota", method = RequestMethod.POST)
	public String delete(Note nota, HttpSession session, HttpServletRequest request) throws IOException {
		DAO dao = new DAO();

		Pessoa usuario = dao.logIn(nota.getPrioridade());

		request.setAttribute("valor", true);
		request.setAttribute("login", usuario.getLogin());

		dao.removeNote(nota);

		return "forward:/efetuaLogin";
	}

	@RequestMapping(value = "editaNota", method = RequestMethod.POST)
	public String update(Note nota, HttpSession session, HttpServletRequest request) throws IOException {
		DAO dao = new DAO();

		Pessoa usuario = dao.logIn(nota.getPrioridade());

		session.setAttribute("userLogin", usuario.getLogin());
		session.setAttribute("noteId", nota.getId());

		return "edita_nota";
	}

	@RequestMapping(value = "paginaUsuario", method = RequestMethod.POST)
	public String voltar(Pessoa pessoa, HttpSession session, HttpServletRequest request) throws IOException {

		request.setAttribute("valor", true);
		request.setAttribute("login", pessoa.getLogin());

		return "forward:/efetuaLogin";
	}

	@RequestMapping(value = "criaNota", method = RequestMethod.POST)
	public String criaNota(Note nota, HttpSession session, HttpServletRequest request) throws IOException {
		DAO dao = new DAO();

		Pessoa usuario = dao.logIn(nota.getPrioridade());

		session.setAttribute("userLogin", usuario.getLogin());

		return "adiciona_nota";
	}

	@RequestMapping(value = "editarNota", method = RequestMethod.POST)
	public String editaNota(Note nota, HttpSession session, HttpServletRequest request) throws IOException {
		DAO dao = new DAO();

		String user_login = nota.getUltima_atualizacao();

		Date agora = new Date();

		String dia = new SimpleDateFormat("dd/MM/yyyy").format(agora);
		dia = dia + " às ";
		String hora = new SimpleDateFormat("HH:mm:ss").format(agora);

		nota.setUltima_atualizacao(dia + hora);

		String prazo = request.getParameter("prazo");
		Date data = null;
		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(prazo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar entrega = Calendar.getInstance();
		entrega.setTime(data);
		nota.setLast_update(entrega);

		dao.altera(nota);

		request.setAttribute("valor", true);
		request.setAttribute("login", user_login);

		return "forward:/efetuaLogin";
	}

	@RequestMapping(value = "adicionarNota", method = RequestMethod.POST)
	public String adicionarNota(Note nota, HttpSession session, HttpServletRequest request) throws IOException {
		DAO dao = new DAO();

		String user_login = request.getParameter("user_login");

		Date agora = new Date();

		String dia = new SimpleDateFormat("dd/MM/yyyy").format(agora);
		dia = dia + " às ";
		String hora = new SimpleDateFormat("HH:mm:ss").format(agora);

		nota.setUltima_atualizacao(dia + hora);

		String prazo = request.getParameter("prazo");
		Date data = null;
		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(prazo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar entrega = Calendar.getInstance();
		entrega.setTime(data);
		nota.setLast_update(entrega);

		dao.adicionaNota(nota);

		request.setAttribute("valor", true);
		request.setAttribute("login", user_login);

		return "forward:/efetuaLogin";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:formulario_login";
	}

}
