<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="mvc.controller.*"%>
<%@page import="mvc.model.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Nova Nota</title>
	</head>
	<body>
		<% 
			String user_login = (String) session.getAttribute("userLogin"); 
		
			DAO dao = new DAO();
			Pessoa user = dao.verificaLogin(user_login);
			
			String user_senha = user.getSenha();
			
		%>
		
		<form action="paginaUsuario" method="post">
			<input type = "hidden" name = "login" value=<%= user_login %>>
			<input type = "hidden" name = "senha" value=<%= user_senha %>>
			<button name="voltar" type="submit">Voltar</button>
		</form><br>
	
		
		
		<form action="adicionarNota" method="post">
			Materia: 
			<select name="categoria"> 
				<option>TechWeb</option>
				<option>EletroMag</option>
				<option>CamadaFisica</option>
				<option>EmpreTec</option>
				<option>ModCom</option>
			</select><br>
			Conteúdo: <input type="text" name='conteudo'><br>
			Prazo (dd/mm/yyyy):  <input type='date' name='prazo'><br>
			Prioridade:
			<select name="prioridade">
				<option>Alta</option>
				<option>Media</option>
				<option>Baixa</option>
			</select><br>
			<input type = "hidden" name = "user_login" value= <%= user_login %> >
			<input type = "hidden" name = "person_id" value= <%= user.getId() %> >
			<input type='submit' value='Criar Nota'>	
		</form>
	
	</body>
</html>