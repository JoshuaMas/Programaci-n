package Actividad_Fichero;

import java.io.*;

public class FitxersDam {

    private BufferedWriter bw;
    private BufferedReader br;

    enum mode {
        lectura,
        escriptura;
    }
    public void fitxer(String path, mode choice) throws IOException {
        if (choice == mode.escriptura) {
            this.bw = new BufferedWriter(new FileWriter(path, true));
        } else if (choice.equals(mode.lectura)) {
            this.br = new BufferedReader(new FileReader(path));
        }
    }

    public boolean escriure(String text)  {
        try {
                this.bw.write(text + "\n");
                this.bw.close();
                return true;
            }

        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public String lectura(int numlinies) throws IOException {
        String lineas;
        for (int i = 0; i < numlinies; i++) {
            lineas = br.readLine();
            System.out.println(lineas);
            this.br.close();
        }
        this.br.close();
        return "Lectura finalizada";
    }
}