package fatec.banco;
import java.io.*;
import java.util.*;
import fatec.loja.*;
/**
 * 
 * @param endereco
 * 		Recebe o endere�o do arquivo
 * 
 * @return
 * 		Cria um novo se não existir e lê caso o arquivo exista
 * 
 */
@SuppressWarnings("serial")
public class BancoPagamento implements Serializable{
	private File arquivo;
	
	public BancoPagamento(String endereco) {
		this.arquivo = new File(endereco + System.getProperty("file.separator") + "usuarios.ser");
	}
	
	/**
	 * 
	 * @return
	 * 		Verifica se o banco já existe
	 * 
	 */
	public boolean bancoExiste() {
		return this.arquivo.exists();
	}
	
	/**
	 *
	 * @return
	 * 		Recupera dados do banco
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Pagamento> recuperarPagamentos() throws IOException, ClassNotFoundException {
		Object objeto = new Object();
		if(arquivo.exists()) {
			FileInputStream entrada = new FileInputStream(this.arquivo);
			ObjectInputStream leitor = new ObjectInputStream(entrada);
			objeto = leitor.readObject();
			leitor.close();
		}else {
			objeto = new ArrayList<Pagamento>();
		}
		return (List<Pagamento>) objeto;
	}
	
	/**
	 * 
	 * @param objeto
	 * 		Recebe um objeto para salvar no banco	
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	public void salvarPagamento(Pagamento objeto) throws IOException, ClassNotFoundException {
		List<Pagamento> lista = this.recuperarPagamentos();
		FileOutputStream saida = new FileOutputStream(this.arquivo);
		ObjectOutputStream escritor = new ObjectOutputStream(saida);
		if(lista.equals(null)) {
			lista = new ArrayList<>();
			lista.add(objeto);
		}else {
			lista.add(objeto);
		}
		escritor.writeObject(lista);
		escritor.close();
	}
}
