package fatec.loja;
import java.io.Serializable;
/**
 * 
 * @param nome
 * 		Recebe o nome do produto (String)
 * @param preco
 * 		Recebe o pre�o do produto (double)
 * @param quantidade
 * 		Recebe a quantidade do produto (int)
 * 
 * @param quantidade
 * 		Recebe a quantidade comprada do produto (int)
 * 
 */
@SuppressWarnings("serial")
public class Produto implements Serializable{
	private String nome;
	private double preco;
	private int quantidade;
	
	public Produto(String nome, double preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPrecoTotal() {
		return this.quantidade * this.preco;
	}
}
