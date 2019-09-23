<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Pagina Inicial</title>
	</head>
	<body>
		<form action="efetuaLogin" method="post">
			Usuario: <input type='text' name='login'><br>
			Senha: <input type="password" name='senha'><br>
			<input type='submit' value='Login'>
		</form>
		<br>
		<form action="registro">
			<button name="cadastro" type="submit"> Cadastro </button>
		</form>	
	</body>
</html>