package Parking;

import java.io.*;
import java.util.ArrayList;


public class Parkingv2_Joshua_Mas {


    enum TipusPlacesParking {
        Discapacitat,
        no_Discapacitat
    }

    // Atributos

    private BufferedReader breader;
    private BufferedWriter bwriter;
    String[] placesDisc;
    String[] placesNoDisc;
    ArrayList<String> cotxes_que_han_partitDisc = new ArrayList<>();
    ArrayList<String> cotxes_que_han_partit = new ArrayList<>();
    private int placesOcupadesNoDisc;
    private int placesOcupadesDisc;


    // Constructores

    public Parkingv2_Joshua_Mas(int places_no_discapacitats, int places_discapacitats) {
        this.placesDisc = new String[places_discapacitats];
        this.placesNoDisc = new String[places_no_discapacitats];
        Places_inicialsDisc();
        Places_inicials();
    }

    // Metodos
    /**Metodo el cual utilizamos para leer un listado de matriculas de un archivo, éste listado
     * lo utilizamos para llenar las dos matrices en las que guardamos las matriculas de los coches
     * Discapacitados y los coches No Discapacitados. */

    public void llegirMatricules(String path) throws Exception {
        try {
            breader = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            throw new Exception("ALERTA =====> Matrícula incorrecte.");
        }
        String lineas = breader.readLine();

        while (lineas != null) {
            /* Creamos 2 booleans para cuando se haya confirmado que lo
             introducido es una matricula con formato correcto siga con la función del metodo.
            */
            boolean bien1 = false;
            boolean bien2 = false;
            /**Creamos varios if para crear un formato en el cual no nos pueden introducir algo que no sea una matricula
             * con el formato 4 digitos y 3 letras, por ej. "1234AAA"
             */
            if (lineas.length() == 7) {
                for (int j = 0; j < 4; j++) {
                    if (Character.isDigit(lineas.charAt(j))) {
                        bien1 = true;
                    } else {
                        throw new Exception("ALERTA =====> Matrícula incorrecte.");
                    }
                }

                for (int j = 0; j < 3; j++) {
                    if (Character.isLetter(lineas.charAt(4 + j)) == true) {
                        bien2 = true;
                    } else {
                        throw new Exception("ALERTA =====> Matrícula incorrecte.");
                    }
                }
            }
            //Con este randomer se van a meter aleatoriamente matriculas en las matrices.
            if (bien1 && bien2) {
                int randomer = (int) ((Math.random() * 2) + 1);
                try{
                if (randomer == 1) {
                    entraCotxe(lineas);
                } else if (randomer == 2) {
                    entraCotxeDiscapacitat(lineas);
                }
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
            lineas = breader.readLine();
        }
        breader.close();
    }
    /** Método en el que utilizamos 1 array para meter las matriculas de los coches, lo utilizamos tanto en el método llegirMatricules
     * como para meter coches de 1 en 1 en el array. */
    public int entraCotxe(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        /**Creamos varios if para crear un formato en el cual no nos pueden introducir algo que no sea una matricula
         * con el formato 4 digitos y 3 letras, por ej. "1234AAA"
         */
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j)) == true) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }
        }
            if (bien1 && bien2) {
                try {
                    porcentajeDeCapacidad(TipusPlacesParking.no_Discapacitat);
                } catch (Exception e) {
                    System.out.println(e);
                }
                for (int i = 0; i < placesNoDisc.length; i++) {
                    if (placesNoDisc[i].equals(matricula)) {
                        throw new Exception("El cotxe ja està al parking. No pot entrar.");
                    } else if (placesNoDisc[i] != "") {
                        if (lleno(TipusPlacesParking.no_Discapacitat)) {
                            throw new Exception("ALERTA =====> Parking per no discapacitats ple.");
                        }
                    } else {
                        for (int j = 0; j < placesNoDisc.length; j++) {
                            if (placesNoDisc[j].equals(matricula)) {
                                throw new Exception("El cotxe ja està al parking. No pot entrar." + i);
                            }
                        }
                        // Comprobamos si en el array en el que guardamos los coches que salen, esta dicho coche, para asi cuando lo queramos sacar, podamos.
                        if (cotxes_que_han_partit.contains(matricula)) {
                            cotxes_que_han_partit.remove(matricula);
                        }
                        // Variable para que haya garrulos.
                        int randomer = (int) ((Math.random()*99)+1);
                        placesNoDisc[i] = matricula;
                        placesOcupadesNoDisc++;
                        if (randomer<10) {
                            entraCotxeDiscapacitat(matricula);
                        }
                        return 0;
                    }
                }
            }

         else {
            throw new Exception("ALERTA =====> Matrícula incorrecte.");
        }
        return 1;
    }
    /** Método en el que utilizamos 1 array para meter las matriculas de los coches discapacitados, lo utilizamos tanto en el método llegirMatricules
     * como para meter coches de 1 en 1 en el array. */
    public int entraCotxeDiscapacitat(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        /**Creamos varios if para crear un formato en el cual no nos pueden introducir algo que no sea una matricula
         * con el formato 4 digitos y 3 letras, por ej. "1234AAA"
         */
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j)) == true) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }
        } else {
            throw new Exception("ALERTA =====> Matrícula incorrecte.");
        }
        if (bien1 && bien2) {
            try {
                porcentajeDeCapacidad(TipusPlacesParking.Discapacitat);
            } catch (Exception e) {
                System.out.println(e);
            }
            for (int i = 0; i < placesDisc.length; i++) {
                if (placesDisc[i].equals(matricula)) {
                    throw new Exception("El cotxe ja està al parking. No pot entrar.");
                } else if (placesDisc[i] != "") {
                    if (lleno(TipusPlacesParking.Discapacitat) && !(lleno(TipusPlacesParking.no_Discapacitat))) {
                        throw new Exception("ALERTA =====> Parking per discapacitats ple. Ha ocupat plaça normal num: " + entraCotxe(matricula));
                    } else if (lleno(TipusPlacesParking.Discapacitat)) {
                        throw new Exception("ALERTA =====> Parking per discapacitats ple.");
                    }
                } else {
                    // Comprobamos si en el array en el que guardamos los coches que salen, esta dicho coche, para asi cuando lo queramos sacar, podamos.
                    if (cotxes_que_han_partitDisc.contains(matricula)) {
                        cotxes_que_han_partitDisc.remove(matricula);
                    }
                    // Bucle para determinar si hay garrulos.
                    for (int j = 0; j < placesNoDisc.length; j++) {
                        if (placesNoDisc[j].equals(matricula)) {
                            placesNoDisc[j] = "";
                            placesDisc[i] = matricula;
                            placesOcupadesDisc++;
                            placesOcupadesDisc--;
                            throw new Exception("ALERTA =====> Garrulo detected!!! Ha aparcat a la plaça: " + i);
                        }
                    }
                    placesDisc[i] = matricula;
                    placesOcupadesDisc++;
                    return i;
                }
            }
        }
        return 1;
    }
    // Metodo que comprueba si el array esta lleno.
    private boolean lleno(TipusPlacesParking tipus) {
        boolean lleno = false;
        if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            for (int i = 0; i < placesDisc.length; i++) {
                if (placesDisc[i] != "") {
                    lleno = true;
                } else if (placesDisc[i] == "") {
                    return false;
                } else {
                    lleno = false;
                }
            }
        } else if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            for (int i = 0; i < placesNoDisc.length; i++) {
                if (placesNoDisc[i] != "") {
                    lleno = true;
                } else if (placesNoDisc[i] == "") {
                    return false;
                } else {
                    lleno = false;
                }
            }
        }
        return lleno;
    }
    // Metodo para saber si las plazas están al 85% de su capacidad.
    private void porcentajeDeCapacidad(TipusPlacesParking tipus) throws Exception {
        if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            int resultat = (int) (placesNoDisc.length * 0.85);
            if (placesOcupadesNoDisc >= resultat) {
                throw new Exception("ALERTA =====> Ocupació de places per no discapacitats supera el 85%.");
            }

        } else if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            int resultat = (int) (placesDisc.length * 0.85);
            if (placesOcupadesNoDisc >= resultat) {
                throw new Exception("ALERTA =====> Ocupació de places per discapacitats supera el 85%.");
            }
        }
    }
    // Metodo para sacar los coches, cada vez que sacamos un coche, lo quitamos del array.
    public void surtCotxe(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        /**Creamos varios if para crear un formato en el cual no nos pueden introducir algo que no sea una matricula
         * con el formato 4 digitos y 3 letras, por ej. "1234AAA"
         */
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j))) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }
        } else {
            throw new Exception("ALERTA =====> Matrícula incorrecte.");
        }
        if (bien1 && bien2) {
            if (cotxes_que_han_partit.contains(matricula)) {
                throw new Exception("El cotxe no és al parking.");
            } else {
                int j = 0;
                for (int i = 0; i < placesNoDisc.length; i++) {
                    if (placesNoDisc[i].equals((matricula))) {
                        cotxes_que_han_partit.add(matricula);
                        j = i;
                    }
                }
                if (cotxes_que_han_partit.contains(matricula)) {
                    placesNoDisc[j] = "";
                    placesOcupadesNoDisc--;
                    System.out.println("Ha salido el coche con matricula " + matricula);
                }
            }
        }
    }
    // Metodo para sacar los coches discapacitados. cada vez que sacamos un coche, lo quitamos del array de discapacitados.
    public void surtCotxeDiscapacitats(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j))) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte.");
                }
            }
        } else {
            throw new Exception("ALERTA =====> Matrícula incorrecte.");
        }
        if (bien1 && bien2) {
            if (cotxes_que_han_partitDisc.contains(matricula)) {
                throw new Exception("El cotxe no és al parking.");
            } else {
                int j = 0;
                for (int i = 0; i < placesDisc.length; i++) {
                    if (placesDisc[i].equals((matricula))) {
                        cotxes_que_han_partitDisc.add(matricula);
                        j = i;
                    }
                }
                if (cotxes_que_han_partitDisc.contains(matricula)) {
                    placesDisc[j] = "";
                    placesOcupadesDisc--;
                    System.out.println("Ha salido el coche con matricula " + matricula);
                }
            }
        }
    }
    // Metodo para saber si las plazas estan ocupadas.
    public int getPlacesOcupades(TipusPlacesParking tipus) {
        int volum = 0;
        if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            volum = placesOcupadesNoDisc;
        } else if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            volum = placesOcupadesDisc;
        }
        return volum;
    }
    // Metodo para saber si hay plazas libres en el array.
    public int getPlacesLliures(TipusPlacesParking tipus) {
        int volum = 0;
        if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            volum = placesDisc.length - placesOcupadesDisc;
        } else if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            volum = placesNoDisc.length - placesOcupadesNoDisc;
        }
        return volum;
    }
    // Guardamos las matriculas guardadas en los 2 arrays en un archivo, indicando la ruta.
    public void guardarMatricules(String path) throws IOException {
        bwriter = new BufferedWriter(new FileWriter(path, true));
        bwriter.write("Matriculas de personas Discapacitadas: \n");
        for (int i = 0; i < placesDisc.length; i++) {
            if (placesDisc[i] != "") {
                this.bwriter.write(placesDisc[i] + "\n");
            }
        }
        bwriter.write("Matriculas de personas NO Discapacitadas: \n");
        for (int j = 0; j < placesNoDisc.length; j++) {
            if (placesNoDisc[j] != "") {

                this.bwriter.write(placesNoDisc[j] + "\n");
            }
        }
        bwriter.close();
    }
    // Metodo para llenar el array de discapacitados con posiciones vacias, para poder controlar las plazas en las que los coches estan guardados.
    private void Places_inicialsDisc() {
        for (int i = 0; i < placesDisc.length; i++) {
            placesDisc[i] = "";

        }
    }
    // Metodo para llenar el array coches no discapacitados con posiciones vacias, para poder controlar las plazas en las que los coches estan guardados.
    private void Places_inicials() {
        for (int i = 0; i < placesNoDisc.length; i++) {
            placesNoDisc[i] = "";
        }

    }
}

