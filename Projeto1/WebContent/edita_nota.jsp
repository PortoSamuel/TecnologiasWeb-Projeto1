<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="br.edu.insper.Model.Pessoa"%>
<%@page import="br.edu.insper.Model.Note"%>
<%@page import="br.edu.insper.Model.DAO"%>
<%@page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Editar Nota</title>
	</head>
	<body>
		<% 
			String user_login = request.getParameter("login"); 
			String  result = request.getParameter("id_nota");
			
			int id_note= Integer.parseInt(result);
			
			DAO dao = new DAO();
			Pessoa user = dao.verificaLogin(user_login);
			Note nota = dao.getNote(id_note);
			
			String user_senha = user.getSenha();
			
			String data = nota.getUltima_atualizacao();
			
		%>
		
		<form action="Logar" method="post">
			<input type = "hidden" name = "login" value=<%= user_login %>>
			<input type = "hidden" name = "senha" value=<%= user_senha %>>
			<button name="voltar" type="submit">Voltar</button>
		</form><br>
		
		<p>Nota Criada por: <%= user.getNome() %> </p>
		<p>Ultima atualizacao: <%= data %> </p>
		
		<h4>Insira as informações a serem atualizadas:</h4>
		
		
		<form action="EditarNota" method="post">
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
			<input type = "hidden" name = "id_nota" value= <%= nota.getId() %> >
			<input type = "hidden" name = "user_login" value= <%= user_login %> >
			<input type='submit' value='Atualizar'>	
		</form>
	
	</body>
</html>