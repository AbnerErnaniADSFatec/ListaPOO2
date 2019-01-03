package fatec.diamante;
public class Diamante {
	char letra;
	
	public Diamante(char letra) {
		this.letra = letra;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}
	
	private String gerarEspacos(int n) {
		if( n == 0 ) {
			return "";
		}else {
			return " " + gerarEspacos(n - 1);
		}
	}
	
	private int getIndexAlfa(char c) {
		int index = 1;
		for(char ch = 'A'; ch != 'Z'; ch++) {
			if( ch == c) {
				break;
			}
			index = index + 1;
		}
		return index;
	}
	
	public String getDiamante() {
		String diamante = "";
		int dentro = 1;
		int fora = getIndexAlfa(this.letra);
		diamante = diamante + gerarEspacos(fora) + 'A' + gerarEspacos(fora) + "\n";
		fora = fora - 1;
		for(char c = 'B'; c < this.letra; c++) {
			diamante = 
					diamante + 
					gerarEspacos(fora) +
					c +
					gerarEspacos(dentro) +
					c +
					gerarEspacos(fora) +
					"\n";
			dentro = dentro + 2;
			fora = fora - 1;
		}
		for(char c = this.letra; c >= 'B'; c--) {
			diamante = 
					diamante + 
					gerarEspacos(fora) +
					c +
					gerarEspacos(dentro) +
					c +
					gerarEspacos(fora) +
					"\n";
			dentro = dentro - 2;
			fora = fora + 1;
		}
		if(this.letra == 'A') {
			fora = fora + 1;
		}
		diamante = diamante + gerarEspacos(fora) + 'A' + gerarEspacos(fora) + "\n";
		return diamante;
	}
}
