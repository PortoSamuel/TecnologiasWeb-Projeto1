package mvc.model;

public class Pessoa {
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private String confirma_senha;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirma_senha() {
		return confirma_senha;
	}

	public void setConfirma_senha(String confirma_senha) {
		this.confirma_senha = confirma_senha;
	}
}
