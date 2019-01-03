package ExtrairEmails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LerEEscreverStream {

	@SuppressWarnings("unused")
	private static int cont = 0;

	public static void main(String[] args) throws Exception {

		Path arquivo = Paths.get(System.getProperty("user.dir") + System.getProperty("file.separator") + "1_Emails")
				.resolve("arquivos").resolve("lista2018.csv");

		Path escreverArquivo = arquivo.resolveSibling("listaEmails2018.txt");

		if (!Files.exists(arquivo)) {
			throw new FileNotFoundException(arquivo.toAbsolutePath().toString());
		}

		long begin = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new FileReader(arquivo.toFile()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(escreverArquivo.toFile()));

		String linha = br.readLine();

		while (linha != null) {
			cont++;
			linha = linha.toLowerCase();
			System.out.println(linha);

			bw.write(linha + "\n");
			bw.flush();

			linha = br.readLine();
		}

		long end = System.currentTimeMillis();

		bw.write("\nTempo de gravação: " + (end - begin) + "ms.");
		System.out.println("\nTempo de gravação: " + (end - begin) + "ms.");
		br.close();
		bw.close();
	}

}
