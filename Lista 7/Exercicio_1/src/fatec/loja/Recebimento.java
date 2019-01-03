package fatec.loja;
import java.io.*;
import fatec.caixa.*;
/**
 * 
 * @param recebimento
 * 		Recebe um cupom fiscal
 * @param caixa
 * 		Recebe um caixa
 * 
 * @return
 * 		Recebimento deposita direto no caixa declarado
 *
 */
@SuppressWarnings("serial")
public class Recebimento implements Serializable{
	private CupomFiscal recebimento;
	
	public Recebimento(CupomFiscal recebimento, Caixa caixa) throws IOException {
		this.recebimento = recebimento;
		caixa.depositar(this.recebimento.calcularTotal());
		caixa.salvarCaixa();
	}
	
	public CupomFiscal getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(CupomFiscal recebimento) {
		this.recebimento = recebimento;
	}
}
