package fatec.imprimir;
import java.io.*;
import java.util.Date;
import java.text.*;
import fatec.banco.*;
import fatec.caixa.*;
import fatec.loja.*;
/**
 * 
 *@param endereco
 *		Recebe um endereÃ§o para salvar o arquivo txt
 *
 *@return
 *		Cria um arquivo texto formatado
 *
 */
@SuppressWarnings("serial")
public class Impressao implements Serializable{
	private File arquivo;
	private Date data;
	private DateFormat formato;
	private String dataImpressao;
	
	public Impressao(String endereco) {
		this.arquivo = new File(endereco + System.getProperty("file.separator") + "Dados.txt");
		this.data = new Date();
		this.formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dataImpressao = formato.format(this.data);
	}
	
	/**
	 * 
	 * @param nome
	 * 		Recebe o nome do estabelecimento
	 * @param ca
	 * 		Recebe um caixa
	 * @param rec
	 * 		Recebe um cupom de recebimento
	 * @param pay
	 * 		Recebe um cupom de pagamento
	 * @throws IOException
	 * 
	 * @throws ClassNotFoundException
	 */
	public void imprimirDados(String nome, Caixa ca, BancoRecebimento rec, BancoPagamento pay) throws IOException, ClassNotFoundException {
		FileWriter escritor = new FileWriter(arquivo);
		BufferedWriter buffer = new BufferedWriter(escritor);
		buffer.write(" ================= Dados do Caixa ==================== ");
		buffer.newLine();
		buffer.append("Data/hora: " + this.dataImpressao);
		buffer.newLine();
		buffer.append("Nome Empresa: " + nome);
		buffer.newLine();
		buffer.append("Saldo Atual do Caixa: " + NumberFormat.getCurrencyInstance().format(ca.getTotalCaixa()));
		buffer.newLine();
		buffer.newLine();
		buffer.append("Pagamentos ___________________________________________ ");
		buffer.newLine();
		buffer.newLine();
		for( Pagamento pagamento : pay.recuperarPagamentos() ) {
			buffer.append("Empresa: " + pagamento.getPagamento().getNomeLoja());
			buffer.newLine();
			buffer.append("CNPJ: " + pagamento.getPagamento().getId());
			buffer.newLine();
			buffer.append("Produtos ========================================= ");
			buffer.newLine();
			for(Produto produto : pagamento.getPagamento().getProdutos()) {
				buffer.append(" _________________________________________________ ");
				buffer.newLine();
				buffer.append(" -> Nome: " + produto.getNome());
				buffer.newLine();
				buffer.append(" -> Preço: " + NumberFormat.getCurrencyInstance().format(produto.getPreco()));
				buffer.newLine();
				buffer.append(" -> Quantidade(und.): " + produto.getQuantidade());
				buffer.newLine();
				buffer.append(" _________________________________________________ ");
				buffer.newLine();
			}
			buffer.newLine();
			buffer.append(" Total: .......................................... " + NumberFormat.getCurrencyInstance().format(pagamento.getPagamento().calcularTotal()));
		}
		buffer.newLine();
		buffer.newLine();
		buffer.append("Recebimentos ___________________________________________ ");
		buffer.newLine();
		buffer.newLine();
		for( Recebimento recebimento : rec.recuperarRecebimentos() ) {
			buffer.append("Cliente: " + recebimento.getRecebimento().getNomeCliente());
			buffer.newLine();
			buffer.append("CPF: " + recebimento.getRecebimento().getId());
			buffer.newLine();
			buffer.append("Produtos ========================================= ");
			buffer.newLine();
			for(Produto produto : recebimento.getRecebimento().getProdutos()) {
				buffer.append(" _________________________________________________ ");
				buffer.newLine();
				buffer.append(" -> Nome: " + produto.getNome());
				buffer.newLine();
				buffer.append(" -> Preço: " + NumberFormat.getCurrencyInstance().format(produto.getPreco()));
				buffer.newLine();
				buffer.append(" -> Quantidade(und.): " + produto.getQuantidade());
				buffer.newLine();
				buffer.append(" _________________________________________________ ");
				buffer.newLine();
			}
			buffer.newLine();
			buffer.append(" Total: .......................................... " + NumberFormat.getCurrencyInstance().format(recebimento.getRecebimento().calcularTotal()));
		}
		buffer.close();
	}
}
