<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Senhas Incoerentes</title>
	</head>
	<body>
	
		<form action="formulario_login">
			<button name="voltar" type="submit">Voltar</button>
		</form><br>
	
	<%
		out.println("Erro ao inserir senha!");
	%>
	
	</body>
</html>