<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="br.edu.insper.Model.Pessoa"%>
<%@page import="br.edu.insper.Model.Note"%>
<%@page import="br.edu.insper.Model.DAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina do usuario</title>
</head>
<body>
	<form action="first_page.html">
		<button name="voltar" type="submit">Voltar</button>
	</form>



	<%
		Boolean valor = (Boolean) request.getAttribute("valor");

		String mensagem = (String) request.getAttribute("mensagem");
		Pessoa result = (Pessoa) request.getAttribute("login");
		
		String user_login = result.getLogin();
		String user_senha = result.getSenha();
	%>
	
	<h3>Bem vindo <%=user_login %>! </h3>
	
	<form action="adiciona_nota.jsp">
	Nova nota:<br>
	<button name="adicionar_nota" type="submit"> Adicionar </button>
	<input type = "hidden" name = "user_login" value=<%= result.getLogin() %>>
	</form><br>
	
	<form action="Logar" method="post">
	Classificar por:
		<select name="classifica"> 
			<option>Nenhum</option>
			<option>Data de Entrega</option>
		</select><br>
	<input type = "hidden" name = "login" value=<%= user_login %>>
	<input type = "hidden" name = "senha" value=<%= user_senha %>>
	<input type='submit' value='Classificar'>
	</form><br>
	
	<form action="Logar" method="post">
	Filtrar por materia:
		<select name="filtra_materia"> 
			<option>Nenhuma</option>
			<option>TechWeb</option>
			<option>EletroMag</option>
			<option>CamadaFisica</option>
			<option>EmpreTec</option>
			<option>ModCom</option>
		</select><br>
	<input type = "hidden" name = "login" value=<%= user_login %>>
	<input type = "hidden" name = "senha" value=<%= user_senha %>>
	<input type='submit' value='Filtrar'>
	</form><br>

	<table border='1'>
		<tr>
			<td><b>Tarefa</b></td>
			<td><b>Data de Entrega</b></td>
			<td><b>Materia</b></td>
			<td><b>Prioridade</b></td>
			<td><b> </b></td>
			<td><b> </b></td>
		</tr>
	
	<%
		String classifica = (String) request.getAttribute("classifica");
		String filtra = (String) request.getAttribute("filtra");
		
		DAO dao = new DAO();
		List<Note> notas = dao.getLista(result, classifica, filtra);
		for (Note nota : notas){ 
	%>

	 <tr>
		 <td><%=nota.getConteudo()%></td>
		 <td><%=nota.getLast_update().getTime()%></td>
		 <td><%=nota.getCategoria()%></td>
		 <td><%=nota.getPrioridade()%></td>
		 <td>
			 <form action="edita_nota.jsp" method="post">
			 	<input type = "hidden" name = "login" value=<%= user_login %>>
			 	<input type = "hidden" name = "id_nota" value=<%= nota.getId() %>>
			 	<input type='submit' value='Editar'>
			 </form>
		 </td>
		 <td>
			 <form action="DeletaNota" method="post">
			 	<input type = "hidden" name = "login" value=<%= user_login %>>
			 	<input type = "hidden" name = "id_nota" value=<%= nota.getId() %>>
			 	<input type='submit' value='Deletar'>
			 </form>
		 </td>
	 </tr>
	 
	
	<% } %>
	
	</table>
	
	
	
	

</body>
</html>