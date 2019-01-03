package fatec.caixa;
import java.io.*;
/**
 * 
 * @param pronto
 * 		Verifica se o caixa j� tem um valor salvo
 * @param endereco
 * 		Recebe o endere�o do arquivo
 * @return
 * 		Cria um caixa caso n�o exista e salva o estado atual;
 *
 */
@SuppressWarnings("serial")
public class Caixa implements Serializable{
	private File arquivo;
	private double totalCaixa;
	
	public Caixa(String endereco) throws ClassNotFoundException, IOException {
		this.arquivo = new File(endereco + System.getProperty("file.separator") + "Caixa.ser");
		if(this.arquivo.exists()){
			FileInputStream entrada = new FileInputStream(this.arquivo);
			ObjectInputStream leitor = new ObjectInputStream(entrada);
			Object objeto = leitor.readObject();
			Caixa caixaPronto = (Caixa) objeto;
			this.totalCaixa = caixaPronto.getTotalCaixa();
			leitor.close();
		}else{
			this.totalCaixa = 100.000;
		}
	}
	
	/**
	 * 
	 * @param valor
	 * 		Recebe o valor a ser retirado
	 * @return
	 * 		Verdadeiro se o saque foi bem sucedido
	 * 
	 */
	public boolean sacar(double valor){
		if( valor > this.totalCaixa){
			return false;
		}else{
			this.totalCaixa = this.totalCaixa - valor;
			return true;
		}
	}
	
	/**
	 * 
	 * @param valor
	 * 		Recebe o valor a ser depositado
	 * @return
	 * 		Verdadeiro se o deposito foi bem sucedido
	 * 
	 */
	public boolean depositar(double valor){
		if(valor > 0){
			this.totalCaixa = this.totalCaixa + valor;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 * @return
	 * 		Salva o estado atual do Caixa
	 * 
	 */
	public void salvarCaixa() throws IOException{
		FileOutputStream saida = new FileOutputStream(this.arquivo);
		ObjectOutputStream escritor = new ObjectOutputStream(saida);
		escritor.writeObject(this);
		escritor.close();
	}
	
	public double getTotalCaixa() {
		return this.totalCaixa;
	}
}
