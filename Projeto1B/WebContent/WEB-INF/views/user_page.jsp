<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="mvc.controller.*"%>
<%@page import="mvc.model.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina do usuario</title>
</head>
<body>
	
	<a href="logout">Sair do sistema</a>
	
	<%
	DAO dao = new DAO();
	
	String user_login = (String) session.getAttribute("usuarioLogado");
	
	Pessoa user = dao.logIn(user_login);

	String user_senha = user.getSenha();
	
	%>
	
	<h3>Bem vindo <%=user_login %>! </h3>
	
	<form action="criaNota" method="post">
	Nova nota:<br>
	<button name="adicionar_nota" type="submit"> Adicionar </button>
	<input type = "hidden" name = "prioridade" value=<%= user.getLogin() %>>
	</form><br>
	
	<form action="efetuaLogin" method="post">
	Classificar por:
		<select name="classifica"> 
			<option>Nenhum</option>
			<option>Data de Entrega</option>
		</select><br>
	<input type = "hidden" name = "nome" value=<%= user_login %>>
	<input type='submit' value='Classificar'>
	</form><br>
	
	<form action="efetuaLogin" method="post">
	Filtrar por materia:
		<select name="filtra_materia"> 
			<option>Nenhuma</option>
			<option>TechWeb</option>
			<option>EletroMag</option>
			<option>CamadaFisica</option>
			<option>EmpreTec</option>
			<option>ModCom</option>
		</select><br>
	<input type = "hidden" name = "nome" value=<%= user_login %>>
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
		
		List<Note> notas = dao.getLista(user, classifica, filtra);
		for (Note nota : notas){ 
	%>

	 <tr>
		 <td><%=nota.getConteudo()%></td>
		 <td><%=nota.getLast_update().getTime()%></td>
		 <td><%=nota.getCategoria()%></td>
		 <td><%=nota.getPrioridade()%></td>
		 <td>
			 <form action="editaNota" method="post">
			 	<input type = "hidden" name = "id" value=<%= nota.getId() %>>
			 	<input type = "hidden" name = "prioridade" value=<%= user.getLogin() %>>
			 	<input type='submit' value='Editar'>
			 </form>
		 </td>
		 <td>
			 <form action="deletaNota" method="post">
			 	<input type = "hidden" name = "id" value=<%= nota.getId() %>>
			 	<input type = "hidden" name = "prioridade" value=<%= user.getLogin() %>>
			 	<input type='submit' value='Deletar'>
			 </form>
		 </td>
	 </tr>
	 
	
	<% } %>
	
	</table>
	
	
	
	

</body>
</html>