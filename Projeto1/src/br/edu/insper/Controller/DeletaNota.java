package br.edu.insper.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.Model.DAO;
import br.edu.insper.Model.Note;

/**
 * Servlet implementation class DeletaNota
 */
@WebServlet("/DeletaNota")
public class DeletaNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletaNota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Note nota = new Note();
		DAO dao = new DAO();

		String login = request.getParameter("login");
		String id_note = request.getParameter("id_nota");
		
		int result = Integer.parseInt(id_note);

		nota = dao.getNote(result);
		
		dao.removeNote(nota);
				
		request.setAttribute("login", login);
		
		RequestDispatcher rd = request.getRequestDispatcher("nota_apagada.jsp");
		rd.forward(request, response);
	}

}
