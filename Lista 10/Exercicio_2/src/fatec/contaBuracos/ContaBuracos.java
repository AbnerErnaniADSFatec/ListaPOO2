package fatec.contaBuracos;
import java.util.*;
public class ContaBuracos {
	private String texto;
	private int buracos;
	
	public ContaBuracos(String texto) {
		this.texto = texto;
		this.buracos = 0;
	}
	
	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	private List<Character> converter(char[] vetor) {
		List<Character> lista = new ArrayList<>();
		for(int i = 0; i < vetor.length; i++) {
			lista.add(vetor[i]);
		}
		return lista;
	}
	
	public int getBuracos() {
		for(char c : texto.toCharArray()) {
			if(converter(("AÁÀÃDOÓÒÕPQRaáãàbdegoóòõpq").toCharArray()).contains(c)) {
				this.buracos = this.buracos + 1;
			}else if(converter(("B").toCharArray()).contains(c)) {
				this.buracos = this.buracos + 2;
			}
		}
		return this.buracos;
	}
}
