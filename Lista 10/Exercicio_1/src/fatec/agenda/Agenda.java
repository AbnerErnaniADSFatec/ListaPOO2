package fatec.agenda;
import java.io.*;
import java.util.*;
public class Agenda {
	private List<Pessoa> pessoas;
	private File arquivo;
	
	@SuppressWarnings("unchecked")
	public Agenda(String nome) throws IOException, ClassNotFoundException {
		this.arquivo = new File(nome);
		if(this.arquivo.exists()) {
			FileInputStream entrada = new FileInputStream(this.arquivo);
			ObjectInputStream leitor = new ObjectInputStream(entrada);
			Object objeto = new Object();
			objeto = leitor.readObject();
			this.pessoas = (List<Pessoa>) objeto;
			leitor.close();
		}else {
			this.pessoas = new ArrayList<>();
		}
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public File getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
	@SuppressWarnings("unused")
	public int contar() {
		int numeroContatos = 0;
		for(Pessoa pessoa : this.getPessoas()) {
			numeroContatos = numeroContatos + 1;
		}
		return numeroContatos;
	}
	
	public boolean adicionar(Pessoa pessoa) {
		try {
			this.pessoas.add(pessoa);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean remover(Pessoa pessoa) {
		try {
			this.pessoas.remove(pessoa);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean salvar() {
		try {
			FileOutputStream saida = new FileOutputStream(this.arquivo);
			ObjectOutputStream escritor = new ObjectOutputStream(saida);
			escritor.writeObject(this.pessoas);
			escritor.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
