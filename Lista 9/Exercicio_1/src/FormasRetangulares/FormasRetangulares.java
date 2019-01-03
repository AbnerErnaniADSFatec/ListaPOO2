package FormasRetangulares;

import javax.swing.JFrame;
import FormasRetangulares.Painel;

public class FormasRetangulares {
	public static void main(String args[]) {
		Painel mp = new Painel();
		JFrame janela = new JFrame("Quadrados");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(400, 400);
		janela.setVisible(true);
		janela.getContentPane();
		janela.add(mp);
	}
}
