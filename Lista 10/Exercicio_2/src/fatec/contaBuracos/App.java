package fatec.contaBuracos;
import javax.swing.JOptionPane;
public class App {
	public static void main(String[] args) {
		String texto = JOptionPane.showInputDialog("Digite um texto: ");
		JOptionPane.showMessageDialog(null, 
				"Esse texto possui " + new ContaBuracos(texto).getBuracos() + " buracos!"
				,"Conta Buracos",1);
	}
}
