package fatec.loja;
import java.util.Date;
import java.io.*;
import java.text.*;
import java.util.List;
import javax.swing.JOptionPane;
/**
 * 
 * @param nomeLoja
 * 		Recebe o nome da loja (String)
 * @param id
 * 		Recebe o id do cliente para exibi-lo na nota fiscal a menos que seja uma empresa (String)
 * @param nomeCliente
 * 		Recebe o nome do cliente que fez a compra (String)
 * @param produtos
 * 		Lista de produtos comprados pelo cliente (List)
 * 
 */
@SuppressWarnings("serial")
public class CupomFiscal implements Serializable{
	private String nomeLoja;
	private Date data;
	private DateFormat dateFormat;
	private String dataCompra;
	private String nomeCliente;
	private String id; 
	private List<Produto> produtos;
	
	public CupomFiscal(String nomeLoja, String id, String nomeCliente, List<Produto> produtos) {
		this.nomeLoja = nomeLoja;
		this.data = new Date();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dataCompra = dateFormat.format(this.data);
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.produtos = produtos;
	}
	
	/**
	 * 
	 * @return
	 * 		Retorna o valor total da compra
	 * 
	 */
	public double calcularTotal() {
		double precoTotal = 0;
		for(Produto produto : produtos) {
			precoTotal = precoTotal + produto.getPrecoTotal();
		}
		//produtos.forEach(produto -> precoTotal = precoTotal + produto.getPreco()); // + (produto.getPreco() * produto.getQuantidade()));
		return precoTotal;
	}
	
	/**
	 * 
	 * @return
	 * 		Retorna uma String com os dados do cupom fiscal
	 */
	public String formatarCupom() {
		String cupomPronto = 
				this.nomeLoja + " --------------------------------------------------------------------------" + "\n" +
				"DATA: " + this.dataCompra + "\n" +
				"CPF: " + this.id + "\n" +
				"NOME: " + this.nomeCliente + "\n" +
				"____________________________________________________" +
				"\n";
		for(Produto produto : this.produtos) {
			cupomPronto = cupomPronto + 
					produto.getNome() + 
					" ............. " + 
					NumberFormat.getCurrencyInstance().format(produto.getPreco()) +
					"_____" + 
					"Quant. " + produto.getQuantidade() +
					"\n";
		}
		cupomPronto = cupomPronto +
				"____________________________________________________" + 
				"\n" +
				"TOTAL: " + NumberFormat.getCurrencyInstance().format(calcularTotal());
		return cupomPronto;
	}
	
	/**
	 * 
	 * Exibe uma janela com a nota fiscal para impress�o e salva no banco
	 * 
	 */
	public void imprimirCupomFiscal() {
		JOptionPane.showMessageDialog(null,this.formatarCupom(),"Cupom Fiscal",2);
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getId() {
		return id;
	}

	public void setCpf(String id) {
		this.id = id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
