package fatec.agenda;
import java.io.IOException;

import javax.swing.JOptionPane;
public class AgendaRemover implements Runnable{
	private Agenda agenda;
	private String nome;
	
	public AgendaRemover(String nome, Agenda agenda) throws ClassNotFoundException, IOException {
		this.agenda = agenda;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public Pessoa excluir() {
		Pessoa excluida = null;
		for(Pessoa pessoa : this.agenda.getPessoas()) {
			if(pessoa.getNome().toUpperCase().contains(nome.toUpperCase())) {
				excluida = pessoa;
				break;
			}
		}
		this.agenda.remover(excluida);
		this.agenda.salvar();
		return excluida;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Essa pessoa foi excluída = " + this.excluir().getNome(), "Agenda", 1);
	}
}
