package ArquivosHD;

import java.io.File;

/**
 * Mostra os diretorios e subdiretorios da user home
 * */

public class Diretorios {

    public void listar(File directory) {

        if(directory.isDirectory())
        {
            System.out.println("Diretorio: " + directory.getPath());
            String[] subDirectory = directory.list();

            if(subDirectory != null)
            {
                for(String dir : subDirectory)
                {
                    listar(new File(directory + File.separator  + dir));
                }
            }
        }

        if(directory.isFile())
        {
            System.out.println("Arquivo: " + directory.getPath());
        }
    }

    public static void main(String x[]) {
        Diretorios dir = new Diretorios();
        dir.listar(new File(System.getProperty("user.home")));
    }
}