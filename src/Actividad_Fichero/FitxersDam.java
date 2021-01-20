package Actividad_Fichero;

import java.io.*;

public class FitxersDam {

    private BufferedWriter bw;
    private BufferedReader br;

    enum mode {
        lectura,
        escriptura;
    }

    public boolean escrit;

    public void fitxer(String path, mode choice) throws IOException {
        if (choice == mode.escriptura) {
            this.bw = new BufferedWriter(new FileWriter(path));
        } else if (choice.equals(mode.lectura)) {
            this.br = new BufferedReader(new FileReader(path));
        }
    }

    public boolean escriure(String text)  {
        try {
            if (text == null) {
                throw new Exception("Escribe algo puto");
            }
            else {
                this.bw.write(text);
                this.bw.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}