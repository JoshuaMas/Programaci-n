package Actividad_Fichero;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
       FitxersDam test = new FitxersDam();
        test.fitxer("archivo.txt", FitxersDam.mode.escriptura);
        System.out.println(test.escriure("Hola mundo!"));

/*
        FitxersDam test = new FitxersDam();
        test.fitxer("/home/alumnesmx/IdeaProjects/Programacion/src/Actividad_Fichero/text.txt", FitxersDam.mode.lectura);
        System.out.println(test.lectura(8));*/
    }
}
