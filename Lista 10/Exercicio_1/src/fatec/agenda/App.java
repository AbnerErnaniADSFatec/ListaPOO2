package fatec.agenda;
import java.io.*;
import javax.swing.*;
public class App {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Thread executor;
		String entrada;
		int opcao;
		Agenda agenda = new Agenda("Agenda.ser");
		do {
			String status;
			if(agenda.getPessoas().isEmpty()) {
				status = "A agenda está vazia!";
			}else {
				status = "Há " + agenda.contar() + " contatos na agenda!";
			}
			entrada = JOptionPane.showInputDialog(
					status + "\n" +
					"------------\n" +
					"Selecione uma opcao:\n" +
					"      (3)...Listar pessoas\n" +
					"      (2)...Remover pessoa\n" +
					"      (1)...Adicionar pessoa\n" +
					"      (0)...sair\n"
			);
			opcao = Integer.parseInt(entrada);
			switch(opcao) {
				case 1:{
					String nome = JOptionPane.showInputDialog("Nome: ");
					String email = JOptionPane.showInputDialog("E-mail: ");
					String telefone = JOptionPane.showInputDialog("Telefone (xx) xxxxx - xxxx: ");
					Pessoa pessoa = new Pessoa(nome,email,telefone);
					AgendaSalvar save = new AgendaSalvar(pessoa, agenda);
					executor = new Thread(save);
					executor.start();
					break;
				}
				case 2:{
					if(agenda.getPessoas().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Não há contatos para remover!!!", "Agenda",1);
						break;
					}else {
						String nome = JOptionPane.showInputDialog("Nome: ");
						AgendaRemover remove = new AgendaRemover(nome, agenda);
						executor = new Thread(remove);
						executor.start();
						break;
					}
				}
				case 3:{
					if(agenda.getPessoas().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Não há contatos para exibir!!!", "Agenda",1);
						break;
					}else {
						for(Pessoa pessoa : agenda.getPessoas()) {
							String formato = 
									"Nome: " + pessoa.getNome() + "\n" +
									"E- mail: " + pessoa.getEmail() + "\n" +
									"Telefone: " + pessoa.getTelefone() + "\n";
							JOptionPane.showMessageDialog(null,formato, "Agenda",1);
						}
						break;
					}
				}
				default:{
					if(opcao == 0) {
						JOptionPane.showMessageDialog(null, "Você escolheu sair!!!", "Agenda",1);
					}else {
						JOptionPane.showMessageDialog(null, "Digite uma opção válida!!!", "Agenda",1);
					}
					break;
				}
			}
		}while(opcao != 0);
	}
}
