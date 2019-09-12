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
 * Servlet implementation class Logar
 */
@WebServlet("/Logar")
public class Logar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logar() {
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
		
		Boolean valor = false;

		Pessoa pessoa = new Pessoa();
		
		DAO dao = new DAO();

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String classifica = request.getParameter("classifica");
		String filtra = request.getParameter("filtra_materia");
		
		request.setAttribute("classifica", classifica);
		request.setAttribute("filtra", filtra);	

		pessoa = dao.logIn(login);

		if (pessoa.getLogin() == null) {
			String retorno = "Usuário não cadastrado";
			
			request.setAttribute("mensagem", retorno);
		} else {
			if(!senha.equals(pessoa.getSenha())) {
				String retorno = "Senha incorreta";
				
				request.setAttribute("mensagem", retorno);
			} else {
			String retorno = "Bem vindo ";
			
			valor = true;
			
			request.setAttribute("mensagem", retorno);
			request.setAttribute("login", pessoa);

			}
		}
		
		if (valor) {
			RequestDispatcher rd = request.getRequestDispatcher("user_page.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("not_user_page.jsp");
			rd.forward(request, response);
		}
		
		dao.close();
	}

}
