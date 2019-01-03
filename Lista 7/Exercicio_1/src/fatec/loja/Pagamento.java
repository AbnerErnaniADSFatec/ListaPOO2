package fatec.loja;
import java.io.*;
import fatec.caixa.*;
/**
 * 
 * @param pagamento
 * 		Recebe um cupom fiscal
 * @param caixa
 * 		Recebe um caixa
 * 
 * @return
 * 		Pagamento faz o saque direto no caixa declarado
 *
 */
@SuppressWarnings("serial")
public class Pagamento implements Serializable{
	private CupomFiscal pagamento;
	
	public Pagamento(CupomFiscal pagamento, Caixa caixa) throws IOException {
		this.pagamento = pagamento;
		caixa.sacar(this.pagamento.calcularTotal());
		caixa.salvarCaixa();
	}
	
	public CupomFiscal getPagamento() {
		return pagamento;
	}

	public void setPagamento(CupomFiscal pagamento) {
		this.pagamento = pagamento;
	}
}
