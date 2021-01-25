package Parking;

import java.io.*;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;


public class Parking_Joshua_Mas {


    enum TipusPlacesParking {
        Discapacitat,
        no_Discapacitat;
    }

    // Atributos

    private BufferedReader breader;
    private BufferedWriter bwriter;
    private int places_no_discapacitats;
    private int places_discapacitats;
    String[] placesDisc;
    String[] placesNoDisc;
    ArrayList<String> cotxes_que_han_partitDisc = new ArrayList<>();
    ArrayList<String> cotxes_que_han_partit = new ArrayList<>();
    private int placesOcupadesNoDisc;
    private int placesOcupadesDisc;


    // Constructors
    public Parking_Joshua_Mas(int places_no_discapacitats, int places_discapacitats) {
        this.places_no_discapacitats = places_no_discapacitats;
        this.places_discapacitats = places_discapacitats;
        this.placesDisc = new String[places_discapacitats];
        this.placesNoDisc = new String[places_no_discapacitats];
        Places_inicialsDisc();
        Places_inicials();
    }

    // Metodos

    public void llegirMatricules(String path) throws Exception {
        try {
            breader = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            throw new Exception("ALERTA =====> Fitxer incorrecte o inexistent");
        }
        String lineas = breader.readLine();

        while (lineas != null) {
            boolean bien1 = false;
            boolean bien2 = false;
            if (lineas.length() == 7) {
                for (int j = 0; j < 4; j++) {
                    if (Character.isDigit(lineas.charAt(j))) {
                        bien1 = true;
                    } else {
                        throw new Exception("ALERTA =====> Matrícula incorrecte");
                    }
                }

                for (int j = 0; j < 3; j++) {
                    if (Character.isLetter(lineas.charAt(4 + j)) == true) {
                        bien2 = true;
                    } else {
                        throw new Exception("ALERTA =====> Matrícula incorrecte");
                    }
                }
            }
            if (bien1 && bien2) {
                int randomer = (int) ((Math.random() * 2) + 1);
                if (randomer == 1) {
                    entraCotxe(lineas);
                } else if (randomer == 2) {
                    entraCotxeDiscapacitat(lineas);
                }
            }
            lineas = breader.readLine();
        }
        breader.close();
    }
    public int entraCotxe(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j)) == true) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
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

                        if (cotxes_que_han_partit.contains(matricula)) {
                            cotxes_que_han_partit.remove(matricula);
                        }
                        int randomer = (int) ((Math.random()*9)+1);
                        placesNoDisc[i] = matricula;
                        placesOcupadesNoDisc++;
                        if (randomer<2) {
                            entraCotxeDiscapacitat(matricula);
                        }
                        return 0;
                    }
                }
            }

         else {
            throw new Exception("ALERTA =====> Matrícula incorrecte");
        }
        return 1;
    }
    public int entraCotxeDiscapacitat(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j)) == true) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }
        } else {
            throw new Exception("ALERTA =====> Matrícula incorrecte");
        }
        if (bien1 && bien2) {
            try {
                porcentajeDeCapacidad(TipusPlacesParking.Discapacitat);
            } catch (Exception e) {
                System.out.println(e);
            }
            for (int i = 0; i < placesDisc.length; i++) {
                if (placesDisc[i].equals(matricula)) {
                    throw new Exception("El cotxe ja està al parking de discapacitats. No pot entrar.");
                } else if (placesDisc[i] != "") {
                    if (lleno(TipusPlacesParking.Discapacitat) && !(lleno(TipusPlacesParking.no_Discapacitat))) {
                        throw new Exception("ALERTA =====> Parking per discapacitats ple. Ha ocupat plaça normal num: " + entraCotxe(matricula));
                    } else if (lleno(TipusPlacesParking.Discapacitat)) {
                        throw new Exception("ALERTA =====> Parking per discapacitats ple");
                    }
                } else {
                    if (cotxes_que_han_partitDisc.contains(matricula)) {
                        cotxes_que_han_partitDisc.remove(matricula);
                    }
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
    private void porcentajeDeCapacidad(TipusPlacesParking tipus) throws Exception {
        if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            int resultat = (int) (placesNoDisc.length * 0.85);
            if (placesOcupadesNoDisc >= resultat) {
                throw new Exception("ALERTA =====> Ocupació de places per no discapacitats supera el 85%");
            }

        } else if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            int resultat = (int) (placesDisc.length * 0.85);
            if (placesOcupadesNoDisc >= resultat) {
                throw new Exception("ALERTA =====> Ocupació de places per discapacitats supera el 85%");
            }
        }
    }
    public void surtCotxe(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j)) == true) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }
        } else {
            throw new Exception("ALERTA =====> Matrícula incorrecte");
        }
        if (bien1 && bien2) {

            if (cotxes_que_han_partit.contains(matricula)) {
                throw new Exception("El cotxe no és al parking");
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
    public void surtCotxeDiscapacitat(String matricula) throws Exception {
        boolean bien1 = false;
        boolean bien2 = false;
        if (matricula.length() == 7) {
            for (int j = 0; j < 4; j++) {
                if (Character.isDigit(matricula.charAt(j))) {
                    bien1 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }

            for (int j = 0; j < 3; j++) {
                if (Character.isLetter(matricula.charAt(4 + j)) == true) {
                    bien2 = true;
                } else {
                    throw new Exception("ALERTA =====> Matrícula incorrecte");
                }
            }
        } else {
            throw new Exception("ALERTA =====> Matrícula incorrecte");
        }
        if (bien1 && bien2) {
            if (cotxes_que_han_partitDisc.contains(matricula)) {
                throw new Exception("El cotxe no és al parking");
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
                }
            }
        }
    }
    public int getPlacesOcupades(TipusPlacesParking tipus) {
        int volum = 0;
        if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            volum = placesOcupadesNoDisc;
        } else if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            volum = placesOcupadesDisc;
        }
        return volum;
    }
    public int getPlacesLliures(TipusPlacesParking tipus) {
        int volum = 0;
        if (tipus.equals(TipusPlacesParking.Discapacitat)) {
            volum = placesDisc.length - placesOcupadesDisc;
            System.out.println("Places Lliures: " + volum);
        } else if (tipus.equals(TipusPlacesParking.no_Discapacitat)) {
            volum = placesNoDisc.length - placesOcupadesNoDisc;
            System.out.println("Places Lliures: " + volum);
        }
        return 0;
    }
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
    private void Places_inicialsDisc() {
        for (int i = 0; i < placesDisc.length; i++) {
            placesDisc[i] = "";

        }
    }
    private void Places_inicials() {
        for (int i = 0; i < placesNoDisc.length; i++) {
            placesNoDisc[i] = "";
        }

    }

}

