<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="br.edu.insper.Model.Pessoa"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verificação</title>
</head>
<body>
	<form action="first_page.html">
		<button name="voltar" type="submit">Voltar</button>
	</form>
	<%
		Boolean valor = (Boolean) request.getAttribute("valor");

		if (valor) {
			String mensagem = (String) request.getAttribute("mensagem");
			Pessoa result = (Pessoa) request.getAttribute("cadastro");
			
			out.println(mensagem + result.getNome());
		} else {
			String mensagem = (String) request.getAttribute("mensagem");
			out.println(mensagem);
		}
	%>
</body>
</html>