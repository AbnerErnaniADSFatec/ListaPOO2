package fatec.agenda;
import java.io.IOException;

import javax.swing.JOptionPane;
public class AgendaSalvar implements Runnable{
	private Agenda agenda;
	private Pessoa pessoa;
	
	public AgendaSalvar(Pessoa pessoa, Agenda agenda) throws ClassNotFoundException, IOException {
		this.agenda = agenda;
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public Pessoa salvar() {
		this.agenda.adicionar(this.pessoa);
		this.agenda.salvar();
		return pessoa;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"Essa pessoa foi salva = " + this.salvar().getNome(), "Agenda", 1);
	}
}
