package ExtrairEmails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ExtrairEmails {

	private static String lerEmail = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "lista2018.csv";
	private static String caminho = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "emails.txt";

	private static final void read(String arq) throws IOException {

		try {
			FileReader fileReader = new FileReader(new File(arq));
			BufferedReader reader = new BufferedReader(fileReader);
			String data = null;

			while ((data = reader.readLine()) != null) {
				if (isValid(data)) {
					System.out.println(data);
				}
			}

			fileReader.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salva as informações em um arquivo de texto
	 * 
	 * @throws IOException
	 */
	private static void saveFile(String reader) throws IOException {

		try {
			long begin = System.currentTimeMillis();
			FileWriter fileWriter = new FileWriter(new File(reader));
			BufferedWriter writer = new BufferedWriter(fileWriter);

			String line = null;
			int c = 0;
			
			writer.write("Arquivo gravado em : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));
			writer.newLine();
			
			while (line != null) {
				c++;
				line = line.toLowerCase();
				
				writer.write(reader+"\n");
				writer.flush();
			}
			long end = System.currentTimeMillis();

			writer.write("Tempo de gravação: " + (end - begin) + "ms.");
			writer.close();
			System.out.println("Arquivo gravado em: " + reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValid(String email) {

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);

		if (email == null)
			return false;

		return pat.matcher(email).matches();
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		try {
			ExtrairEmails.read(lerEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
