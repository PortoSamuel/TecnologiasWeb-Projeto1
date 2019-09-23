<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="mvc.controller.*"%>
<%@page import="mvc.model.*"%>
<%@page import="java.util.*"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Editar Nota</title>
	</head>
	<body>
		<% 
			String user_login = (String) session.getAttribute("userLogin"); 
			Integer  id_note = (Integer) session.getAttribute("noteId");
			
			DAO dao = new DAO();
			Pessoa user = dao.verificaLogin(user_login);
			Note nota = dao.getNote(id_note);
			
			String user_senha = user.getSenha();
			
			String data = nota.getUltima_atualizacao();
			
		%>
		
		<form action="paginaUsuario" method="post">
			<input type = "hidden" name = "login" value=<%= user_login %>>
			<input type = "hidden" name = "senha" value=<%= user_senha %>>
			<button name="voltar" type="submit">Voltar</button>
		</form><br>
		
		<p>Nota Criada por: <%= user.getNome() %> </p>
		<p>Ultima atualizacao: <%= data %> </p>
		
		<h4>Insira as informações a serem atualizadas:</h4>
		
		
		<form action="editarNota" method="post">
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
			<input type = "hidden" name = "id" value= <%= nota.getId() %> >
			<input type = "hidden" name = "person_id" value= <%= user.getId() %> >
			<input type = "hidden" name = "ultima_atualizacao" value= <%= user.getLogin() %> >
			<input type='submit' value='Atualizar'>	
		</form>
	
	</body>
</html>