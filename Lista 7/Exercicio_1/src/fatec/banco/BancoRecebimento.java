package fatec.banco;
import java.io.*;
import java.util.*;
import fatec.loja.*;
/**
 * 
 *@param endereco
 * 		Recebe o endere�o do arquivo
 * 
 * @return
 * 		Cria um novo se não existir e lê caso o arquivo exista
 * 
 */
@SuppressWarnings("serial")
public class BancoRecebimento implements Serializable{
	private File arquivo;
	
	public BancoRecebimento(String endereco) {
		this.arquivo = new File(endereco + System.getProperty("file.separator") + "recebimento.ser");
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
	public List<Recebimento> recuperarRecebimentos() throws IOException, ClassNotFoundException {
		Object objeto = new Object();
		if(arquivo.exists()) {
			FileInputStream entrada = new FileInputStream(this.arquivo);
			ObjectInputStream leitor = new ObjectInputStream(entrada);
			objeto = leitor.readObject();
			leitor.close();
		}else {
			objeto = new ArrayList<Recebimento>();
		}
		return (List<Recebimento>) objeto;
	}
	
	/**
	 * 
	 * @param objeto
	 * 		Recebe um objeto para salvar no banco	
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	public void salvarRecebimento(Recebimento objeto) throws IOException, ClassNotFoundException {
		List<Recebimento> lista = this.recuperarRecebimentos();
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
