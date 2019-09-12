package br.edu.insper.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {
	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto1", "root", "samucasjc1998");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Pessoa verificaLogin(String login) {
		String sql = "SELECT * FROM pessoas WHERE login = (?)";

		Pessoa pessoa = new Pessoa();

		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(1, login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setLogin(rs.getString("login"));
				pessoa.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pessoa;
	}

	public void adicionaPessoa(Pessoa pessoa) {
		String sql = "INSERT INTO pessoas" + "(nome,login,senha) VALUES(?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(1, pessoa.getNome());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(2, pessoa.getLogin());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.setString(3, pessoa.getSenha());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Pessoa logIn(String login) {
		String sql = "SELECT * FROM pessoas WHERE login=(?)";

		Pessoa pessoa = new Pessoa();

		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.setString(1, login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setLogin(rs.getString("login"));
				pessoa.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pessoa;
	}

	public Note getNote(Integer id_note) {
		String sql = "SELECT * FROM notes WHERE id=(?)";

		Note nota = new Note();

		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.setInt(1, id_note);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				nota.setId(rs.getInt("id"));
				nota.setConteudo(rs.getString("conteudo"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("last_update"));
				nota.setLast_update(data);
				nota.setPerson_id(rs.getInt("person_id"));
				nota.setPrioridade(rs.getString("prioridade"));
				nota.setUltima_atualizacao(rs.getString("ultima_atualizacao"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nota;
	}

	public void adicionaNota(Note nota) {
		String sql = "INSERT INTO notes"
				+ "(conteudo,last_update,categoria, person_id, prioridade, ultima_atualizacao) VALUES(?,?,?,?,?,?)";

		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.setString(1, nota.getConteudo());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.setDate(2, new Date(nota.getLast_update().getTimeInMillis()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.setString(3, nota.getCategoria());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt.setInt(4, nota.getPerson_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(5, nota.getPrioridade());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(6, nota.getUltima_atualizacao());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Note> getLista(Pessoa pessoa, String classifica, String filtra) {
		List<Note> notas = new ArrayList<Note>();

		PreparedStatement stmt = null;

		try {
			if (classifica == null && filtra == null) {
				stmt = connection.prepareStatement("SELECT * FROM notes WHERE person_id = (?)");
				stmt.setInt(1, pessoa.getId());
			} else if (classifica != null && filtra == null) {
				if (classifica.equals("Nenhum")) {
					stmt = connection.prepareStatement("SELECT * FROM notes WHERE person_id = (?)");
					stmt.setInt(1, pessoa.getId());
				} else {
					stmt = connection.prepareStatement("SELECT * FROM notes WHERE person_id = (?) ORDER BY last_update");
					stmt.setInt(1, pessoa.getId());
				}
			} else if (classifica == null && filtra != null) {
				if (filtra.equals("Nenhuma")) {
					stmt = connection.prepareStatement("SELECT * FROM notes WHERE person_id = (?)");
					stmt.setInt(1, pessoa.getId());
				} else {
					stmt = connection.prepareStatement("SELECT * FROM notes WHERE person_id = (?) AND categoria = ?");
					stmt.setInt(1, pessoa.getId());
					stmt.setString(2, filtra);
				}
			} else {
				stmt = connection.prepareStatement("SELECT * FROM notes WHERE person_id = (?)");
				stmt.setInt(1, pessoa.getId());
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id"));
				note.setConteudo(rs.getString("conteudo"));
				note.setCategoria(rs.getString("categoria"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("last_update"));
				note.setLast_update(data);
				note.setPerson_id(rs.getInt("person_id"));
				note.setPrioridade(rs.getString("prioridade"));
				notas.add(note);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notas;
	}

	public void removeNote(Note nota) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM notes WHERE id=?");
			stmt.setInt(1, nota.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void altera(Note nota) {
		String sql = "UPDATE notes SET "
				+ "conteudo=?, last_update=?, categoria=?, person_id=?, prioridade=?, ultima_atualizacao=? WHERE id=?";
		PreparedStatement stmt;

		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, nota.getConteudo());
			stmt.setDate(2, new Date(nota.getLast_update().getTimeInMillis()));
			stmt.setString(3, nota.getCategoria());
			stmt.setInt(4, nota.getPerson_id());
			stmt.setString(5, nota.getPrioridade());
			stmt.setString(6, nota.getUltima_atualizacao());
			stmt.setInt(7, nota.getId());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}