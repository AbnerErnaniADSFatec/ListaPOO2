package ArquivosHD;

import java.io.File;

/**
* Mostrar as pastas que se encontram no diretório pessoal
*/

public class Arquivos {
    public String diretorio = System.getProperty("user.home");

    public void visualizarArquivos()
    {
        File file = new File(diretorio);
        File afile[] = file.listFiles();
        int i = 0;

        for(int c = afile.length; i < c; i++)
        {
            File arquivos = afile[i];
            if(arquivos.isDirectory() == true)
            {
                System.out.println("Diretorio: " + arquivos.getName());
            }
            else
            {
                System.out.println("Arquivo: " + arquivos.getName());
            }

        }
    }

    public static void main(String[] args)
    {
        Arquivos arq = new Arquivos();
        arq.visualizarArquivos();
    }
}