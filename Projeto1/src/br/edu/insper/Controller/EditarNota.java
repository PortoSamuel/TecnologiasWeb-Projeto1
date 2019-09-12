package br.edu.insper.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.Model.DAO;
import br.edu.insper.Model.Note;
import br.edu.insper.Model.Pessoa;

/**
 * Servlet implementation class EditarNota
 */
@WebServlet("/EditarNota")
public class EditarNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarNota() {
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
		
		Pessoa person = new Pessoa();
		Note nota = new Note();
		DAO dao = new DAO();
		
		String  result = request.getParameter("id_nota");
		String  user_login = request.getParameter("user_login");
		
		int id_note= Integer.parseInt(result);
		nota.setId(id_note);

		person = dao.verificaLogin(user_login);
		nota.setPerson_id(person.getId());
		
		String categoria = request.getParameter("categoria");
		nota.setCategoria(categoria);
		
		String conteudo = request.getParameter("conteudo");
		nota.setConteudo(conteudo);
		
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
		
		String prioridade = request.getParameter("prioridade");
		nota.setPrioridade(prioridade);
		
		Date agora = new Date();
		
		String dia = new SimpleDateFormat("dd/MM/yyyy").format(agora);
		dia = dia + " às ";
		String hora = new SimpleDateFormat("HH:mm:ss").format(agora);
		
		nota.setUltima_atualizacao(dia + hora);
		
		dao.altera(nota);
		
		String login =  person.getLogin();
		
		request.setAttribute("user_login", login);
		
		RequestDispatcher rd = request.getRequestDispatcher("nota_editada.jsp");
		rd.forward(request, response);
		
		dao.close();
	}

}
