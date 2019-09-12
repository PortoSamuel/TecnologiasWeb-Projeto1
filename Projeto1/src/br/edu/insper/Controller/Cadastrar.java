package br.edu.insper.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.Model.DAO;
import br.edu.insper.Model.Pessoa;

/**
 * Servlet implementation class Adiciona
 */
@WebServlet("/Adiciona")
public class Cadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cadastrar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		Pessoa pessoa = new Pessoa();

		DAO dao = new DAO();

		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String confirm_senha = request.getParameter("confirm_senha");

		Pessoa verifica = dao.verificaLogin(login);

		if (verifica.getLogin() != null) {
			String retorno = "Login já em uso";

			request.setAttribute("valor", false);
			request.setAttribute("mensagem", retorno);
		} else {
			if (senha.equals(confirm_senha)) {
				String retorno = "Cadastro realizado com sucesso ";
				
				pessoa.setNome(nome);
				pessoa.setLogin(login);
				pessoa.setSenha(senha);
				
				dao.adicionaPessoa(pessoa);

				request.setAttribute("valor", true);
				request.setAttribute("mensagem", retorno);
				request.setAttribute("cadastro", pessoa);
			} else {
				String retorno = "As senhas não batem";

				request.setAttribute("valor", false);
				request.setAttribute("mensagem", retorno);
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("verifica_cadastro.jsp");
		rd.forward(request, response);

		dao.close();
	}

}
