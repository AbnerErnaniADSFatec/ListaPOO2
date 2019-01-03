package fatec.autenticacao;
/**
 * @param user
 * 		usuario da classe usuario
 * 
 * @return
 * 		Cria um modelo de autentica��o para o usu�rio do sistema
 *
 */
public class Authentication {
	Usuario user;
	
	public Authentication(Usuario user) {
		this.user = user;
	}
	
	/**
	 * 
	 * @return
	 * 		Retorna falso se o usu�rio n�o estiver logado e verdadeiro caso estiver
	 * 
	 */
	public boolean isAuthenticated() {
		if( this.user == null) {
			return false;
		}else if(this.user.getUsuario().equals("admin") && this.user.getSenha().equals("admin")){
			return true;
		}else{
			return true;
		}
	}
}
