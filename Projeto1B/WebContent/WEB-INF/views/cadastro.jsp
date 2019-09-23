<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %>

<!DOCTYPE html>
	<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Cadastro</title>
	</head>
		<body>		
		<form action="adiciona" method="post">
			Nome: <input type='text' name='nome'><br>
			Login: <input type="text" name='login'><br>
			Senha: <input type="password" name='senha'><br>
			Confirmar Senha: <input type="password" name='confirma_senha'><br>
			<input type='submit' value='Submit'>
		</form>
		</body>
</html>