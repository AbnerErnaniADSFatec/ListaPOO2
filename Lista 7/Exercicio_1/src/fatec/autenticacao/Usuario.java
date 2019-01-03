package fatec.autenticacao;
import java.io.Serializable;
/**
 * 
 * @param usuario
 * 		usuario (String)
 * @param senha
 * 		senha (String)
 * 
 * @return
 * 		cria um usuario
 *
 */
@SuppressWarnings("serial")
public class Usuario implements Serializable{
	private String usuario;
	private String senha;
	
	public Usuario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
