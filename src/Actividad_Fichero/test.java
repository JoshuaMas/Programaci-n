package Actividad_Fichero;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        FitxersDam test = new FitxersDam();
        test.fitxer("test.txt", FitxersDam.mode.escriptura);
        System.out.println(test.escriure("soy malisimo"));
    }
}
