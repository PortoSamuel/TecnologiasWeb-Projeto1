<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Algo deu errado</title>
	</head>
	<body>
	
		<form action="first_page.html">
			<button name="voltar" type="submit">Voltar</button>
		</form>
	
	<%
		String mensagem = (String) request.getAttribute("mensagem");
		out.println(mensagem);
	%>
	
	</body>
</html>