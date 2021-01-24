package Parking;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Parking_Joshua_Mas p = new Parking_Joshua_Mas(5, 5);

        try {

        p.entraCotxeDiscapacitat("1234ABC");
        p.entraCotxeDiscapacitat("1234ACC");
        System.out.println(p.getPlacesOcupades(Parking_Joshua_Mas.TipusPlacesParking.Discapacitat));

            p.llegirMatricules(args[0]);
        }catch(Exception e){
                System.out.println(e); }

        System.out.println(p.getPlacesOcupades(Parking_Joshua_Mas.TipusPlacesParking.Discapacitat));
        System.out.println(p.getPlacesOcupades(Parking_Joshua_Mas.TipusPlacesParking.no_Discapacitat));
            Scanner teclado = new Scanner(System.in);

            boolean bandera = false;
            while (!bandera) {
                System.out.println("1.- Omplir parking a partir de fitxer");
                System.out.println("2.- Entrar Cotxe");
                System.out.println("3.- Entrar Cotxe Discapacitado");
                System.out.println("4.- Surtir Cotxe");
                System.out.println("5.- Surtir Cotxe Discapacitado");
                System.out.println("6.- Guardar llistat de matricules en fitxer");
                System.out.println("7.- Sortir");
                int seleccio = teclado.nextInt();
                switch (seleccio) {
                    case 1 -> {
                        p.llegirMatricules(args[0]);
                    }
                    case 2 -> {
                        String a = teclado.next();
                        p.entraCotxe(a);

                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                    case 5 -> {

                    }
                    case 6 -> {

                    }
                    case 7 -> {

                    }


                }

            }
        }
    }






