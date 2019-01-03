package fatec.diamante;
import javax.swing.JOptionPane;
public class App {
	public static void main(String[] args) {
		String entrada = JOptionPane.showInputDialog("Insira uma letra: ");
		char letra = entrada.toUpperCase().toCharArray()[entrada.toUpperCase().toCharArray().length - 1];
		Diamante diamante = new Diamante(letra);
		JOptionPane.showMessageDialog(null, diamante.getDiamante(), "Diamante", 1);
	}
}
