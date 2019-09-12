<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="br.edu.insper.Model.Pessoa"%>
<%@page import="br.edu.insper.Model.DAO"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Nota apagada</title>
	</head>
	<body>
		<%
			String user_login = request.getParameter("login");
		
			DAO dao = new DAO();
			Pessoa user = dao.verificaLogin(user_login);
			
			String user_senha = user.getSenha();
		%>
		
		<form action="Logar" method="post">
			<input type = "hidden" name = "login" value=<%= user_login %>>
			<input type = "hidden" name = "senha" value=<%= user_senha %>>
			<button name="voltar" type="submit">Voltar</button>
		</form> <br>
		
		<% String mensagem = "Nota apagada com sucesso!"; %>
		<% out.println(mensagem); %>
		
	</body>
</html>