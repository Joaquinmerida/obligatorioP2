package primerobligatoriop2;

import java.util.*;
import primerobligatoriop2.Utilidades.*;

public class PrimerObligatorioP2 {

    public static void main(String[] args) {
        System.out.println("Presiona R para registrar jugador");
        System.out.println("Presiona S para jugar modo Simple");
        System.out.println("Presiona F para jugar modo Full");
        System.out.println("Presiona D para generar un report de juego");
        System.out.println("Presiona E para salir del juego");
        Scanner input = new Scanner(System.in);
        String ingresoMenu = input.next();
        input.nextLine();
        String ganador = "";
        String saltoGatitos = "";
        int contadorTurnos = 0;
        ArrayList<Perfiles> personas = new ArrayList<>();
        String[][] mat = new String[6][6];
        int[] cajaJugadores = {8, 0, 8, 0};
        boolean finJuego = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = "0";
            }
        }
        while (ingresoMenu.charAt(0) == 'D' || ingresoMenu.charAt(0) == 'R') {
            /*
            GENERAR PDF 
            if(ingresoMenu.charAt(0) ==  'D'){
            LISTA DE JUGADORES ORDENADA ALF
            Y CANTIDAD DE PARTIDAS QUE JUGO CADA UNO
            }
             */
            if (ingresoMenu.charAt(0) == 'R') {
                System.out.println("Ingrese su nombre: ");
                String nombre = input.nextLine();
                System.out.println("Ingrese su edad: ");
                int edad = input.nextInt();
                input.nextLine();
                System.out.println("Ingrese su alias: ");
                String alias = input.nextLine();
                //checkear que el alias sea unico
                Perfiles nuevoPerfil = new Perfiles(nombre, edad, alias);
                personas.add(nuevoPerfil);
                System.out.println(nombre + " " + edad + " " + alias);
                //IMPRIME ARRAY DE PERSONAS INGRESADAS
                for (Perfiles persona : personas) {
                    System.out.println(persona.getNombre() + ", " + persona.getEdad() + " años, alias " + persona.getAlias());
                }
            }
            System.out.println("Presiona R para registrar jugador");
            System.out.println("Presiona S para jugar modo Simple");
            System.out.println("Presiona F para jugar modo Full");
            System.out.println("Presiona D para generar un report de juego");
            System.out.println("Presiona E para salir del juego");
            ingresoMenu = input.next();
            input.nextLine();
        }

        // MODO SIMPLE _______________________________________________________________________________________________
        if (ingresoMenu.charAt(0) == 'S') {
            while (!finJuego) {
                if (contadorTurnos % 2 == 0) {
                    System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                    String coordenadaX = input.next();
                    int coordenadaY = input.nextInt();
                    int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    String letrasCheck = "ABCDEFabcdefg";
                    Boolean xBien = letrasCheck.contains(coordenadaX);
                    Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                    System.out.println(xBien + " " + yBien);
                    System.out.println(coordenadaX);
                    while (!(xBien && yBien)) {
                        System.out.println("Coordenadas incorrectas");
                        System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                        coordenadaX = input.next();
                        coordenadaY = input.nextInt();
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        xBien = letrasCheck.contains(coordenadaX);
                        yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                    }
                    coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);

                    while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                        System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gatito");
                        coordenadaX = input.next();
                        coordenadaY = input.nextInt();
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    }
                    MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "rojo");
                    MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                    saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                    MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                    cajaJugadores[0]--;
                } else {
                    System.out.println("JUGADOR AZUL: Coloque las coordenadas del nuevo gatito");
                    String coordenadaX = input.next();
                    int coordenadaY = input.nextInt();
                    int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    String letrasCheck = "ABCDEFabcdefg";
                    Boolean xBien = letrasCheck.contains(coordenadaX);
                    Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                    System.out.println(xBien + " " + yBien);
                    System.out.println(coordenadaX);
                    while (!(xBien && yBien)) {
                        System.out.println("Coordenadas incorrectas");
                        System.out.println("JUGADOR AZUL: Coloque las coordenadas del nuevo gatito");
                        coordenadaX = input.next();
                        coordenadaY = input.nextInt();
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        xBien = letrasCheck.contains(coordenadaX);
                        yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                    }
                    coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                        System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gatito");
                        coordenadaX = input.next();
                        coordenadaY = input.nextInt();
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    }
                    MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "azul");
                    MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                    saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                    MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                    cajaJugadores[2]--;
                }

                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[0].length; j++) {
                        if (mat[i][j].contains("rojo")) {
                            System.out.print("\u001B[31m" + mat[i][j].charAt(0) + " ");
                        } else {
                            System.out.print("\u001B[34m" + mat[i][j].charAt(0) + " ");
                        }
                    }
                    System.out.println();
                }

                System.out.println("\u001B[31m" + "GATITOS RESTANTES: " + cajaJugadores[0]);
                System.out.println("\u001B[34m" + "GATITOS RESTANTES: " + cajaJugadores[2]);
                if (contadorTurnos == 20) {
                    finJuego = true;
                }
                contadorTurnos++;
                if (hayGanador(mat).length() > 1) {
                    System.out.println("Felicitaciones jugador " + hayGanador(mat).substring(0, 4) + " sos el ganador!!");
                }
            }
        }

        // MODO FULL _______________________________________________________________________________________________
        if (ingresoMenu.charAt(0) == 'F') {
            String inputJugador = "";
            while (!finJuego) {
                jugadaCorrectaLoop:
                if (contadorTurnos % 2 == 0) {
                    if (cajaJugadores[1] > 0) {
                        System.out.println("Ingrese  -g-  para colocar un gatito");
                        System.out.println("Ingrese -G- para colocar un gato");
                        inputJugador = input.next();
                    } else {

                        System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                        String coordenadaX = input.next();
                        int coordenadaY = input.nextInt();
                        int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        String letrasCheck = "ABCDEFabcdefg";
                        Boolean xBien = letrasCheck.contains(coordenadaX);
                        Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                        System.out.println(xBien + " " + yBien);
                        System.out.println(coordenadaX);
                        while (!(xBien && yBien)) {
                            System.out.println("Coordenadas incorrectas");
                            System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            xBien = letrasCheck.contains(coordenadaX);
                            yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                        }
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);

                        while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                            System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gatito");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        }

                        MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "rojo");
                        MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                        cajaJugadores[0]--;
                        contadorTurnos++;
                    }
                    if (inputJugador.contains("g")) {
                        System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                        String coordenadaX = input.next();
                        int coordenadaY = input.nextInt();
                        int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        String letrasCheck = "ABCDEFabcdefg";
                        Boolean xBien = letrasCheck.contains(coordenadaX);
                        Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                        System.out.println(xBien + " " + yBien);
                        System.out.println(coordenadaX);
                        while (!(xBien && yBien)) {
                            System.out.println("Coordenadas incorrectas");
                            System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            xBien = letrasCheck.contains(coordenadaX);
                            yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                        }
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);

                        while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                            System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gatito");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        }
                        MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "rojo");
                        MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                        cajaJugadores[0]--;
                        contadorTurnos++;

                    }

                    if (inputJugador.contains("G")) {
                        System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gato");
                        String coordenadaX = input.next();
                        int coordenadaY = input.nextInt();
                        int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        String letrasCheck = "ABCDEFabcdefg";
                        Boolean xBien = letrasCheck.contains(coordenadaX);
                        Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                        System.out.println(xBien + " " + yBien);
                        System.out.println(coordenadaX);
                        while (!(xBien && yBien)) {
                            System.out.println("Coordenadas incorrectas");
                            System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gato");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            xBien = letrasCheck.contains(coordenadaX);
                            yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                        }
                        coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);

                        while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                            System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gato");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        }
                        MovimientoGatitos.colocarGato(coordenadas[0], coordenadas[1], mat, "rojo");
                        saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "G");
                        MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                        cajaJugadores[1]--;
                        contadorTurnos++;
                    }

                    Boolean finalPor8Gatos = false;
                    if (cajaJugadores[0] < 8 && cajaJugadores[1] == 0) {
                        finalPor8Gatos = todoGatosGrandes(mat, "rojo");
                        if (!finalPor8Gatos) {
                            Boolean jugadaCorrecta = false;
                            while (!jugadaCorrecta) {
                                System.out.println("JUGADOR ROJO: te has quedado sin gatitos ni gatos. Solo puedes usar las reglas adicionales");
                                System.out.println("Ingresa RA1 o RA2 para utilizar las reglas adicionales");
                                System.out.println("RA1 + coordenadas = Quitar un gatito de la colcha y convertirlo en gato");
                                System.out.println("RA2 + coordenadas= Quitar un gato de la colcha y guardarlo en la caja");
                                String inputReglasAdicionales = input.next();

                                String letrasCheck = "ABCDEFabcdefg";
                                System.out.println("compara" + inputReglasAdicionales.substring(3, 4) + "numero" + Character.getNumericValue(inputReglasAdicionales.charAt(4)));
                                Boolean xBien = letrasCheck.contains(inputReglasAdicionales.substring(3, 4));
                                Boolean yBien = (Character.getNumericValue(inputReglasAdicionales.charAt(4)) >= 0 && Character.getNumericValue(inputReglasAdicionales.charAt(4)) <= 5);
                                System.out.println(xBien + " " + yBien);

                                while ((!inputReglasAdicionales.contains("RA1") && !inputReglasAdicionales.contains("RA2")) || inputReglasAdicionales.length() != 5) {
                                    System.out.println("JUGADOR ROJO: te has quedado sin gatitos ni gatos.");
                                    System.out.println("Solo puedes usar las reglas adicionales");
                                    System.out.println("Ingresa RA1 o RA2 para utilizar las reglas adicionales");
                                    System.out.println("RA1+ coordenadas = Quitar un gatito de la colcha y convertirlo en gato");
                                    System.out.println("RA2+ coordenadas = Quitar un gato de la colcha y guardarlo en la caja");
                                    inputReglasAdicionales = input.next();
                                    xBien = letrasCheck.contains(inputReglasAdicionales.substring(3, 4));
                                    yBien = (Character.getNumericValue(inputReglasAdicionales.charAt(4)) >= 0 && Character.getNumericValue(inputReglasAdicionales.charAt(4)) <= 5);
                                }

                                //
                                //
                                //
                                //
                                //
                                //
                                //
                                ///
                                ///
                                //
                                while(!(xBien && yBien)) {
                                    xBien = letrasCheck.contains(inputReglasAdicionales.substring(3, 4));
                                    yBien = (Character.getNumericValue(inputReglasAdicionales.charAt(4)) >= 0 && Character.getNumericValue(inputReglasAdicionales.charAt(4)) <= 5);
                               
                                    int[] coordenadas = MovimientoGatitos.getCoordenadas(inputReglasAdicionales.substring(3, 4), Character.getNumericValue(inputReglasAdicionales.charAt(4)));
                                    if (inputReglasAdicionales.contains("RA1")) {
                                        if (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("rojo")) {
                                            MovimientoGatitos.sacaGatitoConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                            cajaJugadores[1]++;
                                            jugadaCorrecta = true;
                                            break jugadaCorrectaLoop;
                                        } else {
                                            System.out.println("No hay gatitos en esa posicion!!");
                                        }
                                    }
                                    if (inputReglasAdicionales.contains("RA2")) {
                                        if (MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("rojo")) {
                                            MovimientoGatitos.sacaGatitoConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                            cajaJugadores[1]++;
                                            jugadaCorrecta = true;
                                            break jugadaCorrectaLoop;
                                        } else {
                                            System.out.println("comparo: " + coordenadas[0] + "y " + coordenadas[1]);
                                            System.out.println("No hay un gato en esa posicion");
                                        }
                                    }
                                    xBien = letrasCheck.contains(inputReglasAdicionales.substring(3, 4));
                                    yBien = (Character.getNumericValue(inputReglasAdicionales.charAt(4)) >= 0 && Character.getNumericValue(inputReglasAdicionales.charAt(4)) <= 5);
                                
                                }

                                /*
                                
                                int[] coordenadas = MovimientoGatitos.getCoordenadas(inputReglasAdicionales.substring(3, 4), Character.getNumericValue(inputReglasAdicionales.charAt(4)));

                                if (inputReglasAdicionales.contains("RA1")) {
                                    if (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("rojo")) {
                                        MovimientoGatitos.sacaGatitoConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                        cajaJugadores[1]++;
                                        jugadaCorrecta = true;
                                        break jugadaCorrectaLoop;
                                    } else {
                                        System.out.println("No hay gatitos en esa posicion!!");
                                    }
                                }
                                if (inputReglasAdicionales.contains("RA2")) {
                                    if (MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("rojo")) {
                                        MovimientoGatitos.sacaGatitoConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                        cajaJugadores[1]++;
                                        jugadaCorrecta = true;
                                        break jugadaCorrectaLoop;
                                    } else {
                                        System.out.println("comparo: " + coordenadas[0] + "y " + coordenadas[1]);
                                        System.out.println("No hay un gato en esa posicion");
                                    }
                                }
                                 */
                            }
                        } else {
                            System.out.println("Gano jugador rojo porque tiene 8 gatos en caja");
                            finJuego = true;
                        }
                    }
                    inputJugador = "";
                } else {
                    if (cajaJugadores[3] > 0) {
                        System.out.println("Ingrese  -g-  para colocar un gatito");
                        System.out.println("Ingrese -G- para colocar un gato");
                        inputJugador = input.next();
                    } else {
                        System.out.println("JUGADOR azul: Coloque las coordenadas del nuevo gatito");
                        String coordenadaX = input.next();
                        int coordenadaY = input.nextInt();
                        int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                            System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gatito");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        }
                        MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "azul");
                        MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                        cajaJugadores[2]--;
                        contadorTurnos++;
                    }
                    if (inputJugador.contains("g")) {
                        System.out.println("JUGADOR azul: Coloque las coordenadas del nuevo gatito");
                        String coordenadaX = input.next();
                        int coordenadaY = input.nextInt();
                        int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                            System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gatito");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        }
                        MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "azul");
                        MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                        MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                        cajaJugadores[2]--;
                        contadorTurnos++;
                    }
                    if (inputJugador.contains("G")) {
                        System.out.println("JUGADOR azul: Coloque las coordenadas del nuevo gato");
                        String coordenadaX = input.next();
                        int coordenadaY = input.nextInt();
                        int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                            System.out.println("YA HAY UN GATITO EN ESA CASILLA, VUELVA A COLOCAR las coordenadas del nuevo gato");
                            coordenadaX = input.next();
                            coordenadaY = input.nextInt();
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                        }
                        MovimientoGatitos.colocarGato(coordenadas[0], coordenadas[1], mat, "azul");
                        saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "G");
                        MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                        cajaJugadores[3]--;
                        contadorTurnos++;
                    }
                    Boolean finalPor8Gatos = false;
                    if (cajaJugadores[0] == 0 && cajaJugadores[3] == 0) {
                        finalPor8Gatos = todoGatosGrandes(mat, "rojo");
                        if (!finalPor8Gatos) {
                            Boolean jugadaCorrecta = false;
                            while (!jugadaCorrecta) {
                                System.out.println("JUGADOR AZUL: te has quedado sin gatitos ni gatos. Solo puedes usar las reglas adicionales");
                                System.out.println("Ingresa RA1 o RA2 para utilizar las reglas adicionales");
                                System.out.println("RA1 + coordenadas = Quitar un gatito de la colcha y convertirlo en gato");
                                System.out.println("RA2 + coordenadas= Quitar un gato de la colcha y guardarlo en la caja");
                                String inputReglasAdicionales = input.next();
                                while ((!inputReglasAdicionales.contains("RA1") && !inputReglasAdicionales.contains("RA2")) || inputReglasAdicionales.length() != 5) {
                                    System.out.println("JUGADOR AZUL: te has quedado sin gatitos ni gatos.");
                                    System.out.println("Solo puedes usar las reglas adicionales");
                                    System.out.println("Ingresa RA1 o RA2 para utilizar las reglas adicionales");
                                    System.out.println("RA1+ coordenadas = Quitar un gatito de la colcha y convertirlo en gato");
                                    System.out.println("RA2+ coordenadas = Quitar un gato de la colcha y guardarlo en la caja");
                                    inputReglasAdicionales = input.next();
                                }
                                int[] coordenadas = MovimientoGatitos.getCoordenadas(inputReglasAdicionales.substring(3, 4), Character.getNumericValue(inputReglasAdicionales.charAt(4)));
                                if (inputReglasAdicionales.contains("RA1")) {
                                    if (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("azul")) {
                                        MovimientoGatitos.sacaGatitoConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                        cajaJugadores[3]++;
                                        jugadaCorrecta = true;
                                        break jugadaCorrectaLoop;
                                    } else {
                                        System.out.println("No hay gatitos en esa posicion!!");
                                    }
                                }
                                if (inputReglasAdicionales.contains("RA2")) {
                                    if (MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("azul")) {
                                        MovimientoGatitos.sacaGatitoConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                        cajaJugadores[3]++;
                                        jugadaCorrecta = true;
                                        break jugadaCorrectaLoop;
                                    } else {
                                        System.out.println("comparo: " + coordenadas[0] + "y " + coordenadas[1]);
                                        System.out.println("No hay un gato en esa posicion");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Gano jugador rojo porque tiene 8 gatos en caja");
                            finJuego = true;
                        }
                    }

                    inputJugador = "";
                }

                if (hayGanador(mat).length() > 5) {
                    String color = hayGanador(mat).substring(0, 4);
                    String datosGanador = hayGanador(mat).substring(5);
                    String[] coordenadas = datosGanador.split(" ");
                    System.out.println(tieneGatoGrande(mat, coordenadas));
                    MovimientoGatitos.agregaGatoACaja(color, cajaJugadores, 3);
                    Boolean ganoFull = tieneGatoGrande(mat, coordenadas) == 3;
                    MovimientoGatitos.desaparecerGatitos(mat, coordenadas);
                    if (ganoFull) {
                        System.out.println(color + " GANO CON GATOS GRANDES");
                        finJuego = true;
                    }
                }

                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[0].length; j++) {
                        if (mat[i][j].contains("rojo")) {
                            System.out.print("\u001B[31m" + mat[i][j].charAt(0) + " ");
                        } else {
                            System.out.print("\u001B[34m" + mat[i][j].charAt(0) + " ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("\u001B[31m" + "GATITOS RESTANTES: " + cajaJugadores[0]);
                System.out.println("\u001B[34m" + "GATITOS RESTANTES: " + cajaJugadores[2]);
                System.out.println(cajaJugadores[1] + " rojo");
                System.out.println(cajaJugadores[3] + " azul");
            }
        }
    }

    public static String hayGanador(String[][] mat) {
        Boolean hayGanador = false;
        String color = "";
        Boolean horizontal = false;
        Boolean vertical = false;
        Boolean diagonalArribaAbajoIzquierdaDerecha = false;
        Boolean diagonalArribaAbajoDerechaIzquierda = false;
        String coordenadas = "";
        for (int i = 0; i < mat.length && !hayGanador; i++) {
            for (int j = 0; j < mat[0].length && !hayGanador; j++) {

                if (mat[i][j].charAt(0) == 'g' || mat[i][j].charAt(0) == 'G') {
                    color = mat[i][j].substring(1);
                    if (j + 2 <= mat[0].length - 1) {
                        //horizontal = mat[i][j + 1].charAt(0) == 'g' && mat[i][j + 1].contains(color) && (mat[i][j + 2].charAt(0) == 'g' && mat[i][j + 2].contains(color));
                        horizontal = (mat[i][j + 1].charAt(0) == 'g' || mat[i][j + 1].charAt(0) == 'G') && mat[i][j + 1].contains(color) && (mat[i][j + 2].charAt(0) == 'g' || mat[i][j + 2].charAt(0) == 'G') && mat[i][j + 2].contains(color);
                        if (i + 2 <= mat[0].length - 1) {
                            //diagonalArribaAbajoIzquierdaDerecha = (mat[i + 1][j + 1].charAt(0) == 'g' || mat[i + 1][j + 1].charAt(0) == 'g' && mat[i + 1][j + 1].contains(color)) && (mat[i + 2][j + 2].charAt(0) == 'g' || mat[i + 2][j + 2].charAt(0) == 'G' && mat[i + 2][j + 2].contains(color));
                            diagonalArribaAbajoIzquierdaDerecha = (mat[i + 1][j + 1].charAt(0) == 'g' || mat[i + 1][j + 1].charAt(0) == 'G') && mat[i + 1][j + 1].contains(color) && (mat[i + 2][j + 2].charAt(0) == 'g' || mat[i + 2][j + 2].charAt(0) == 'G') && mat[i + 2][j + 2].contains(color);
                        }
                    }
                    if (i + 2 <= mat.length - 1) {
                        //vertical = (mat[i + 1][j].charAt(0) == 'g' || mat[i + 1][j].charAt(0) == 'G' && mat[i + 1][j].contains(color)) && (mat[i + 2][j].charAt(0) == 'g' || mat[i + 2][j].charAt(0) == 'G' && mat[i + 2][j].contains(color));
                        vertical = (mat[i + 1][j].charAt(0) == 'g' || mat[i + 1][j].charAt(0) == 'G') && mat[i + 1][j].contains(color) && (mat[i + 2][j].charAt(0) == 'g' || mat[i + 2][j].charAt(0) == 'G') && mat[i + 2][j].contains(color);
                        if (j - 2 >= 0) {
                            //diagonalArribaAbajoDerechaIzquierda = (mat[i + 1][j - 1].charAt(0) == 'g' || mat[i + 1][j - 1].charAt(0) == 'G' && mat[i + 1][j - 1].contains(color)) && (mat[i + 2][j - 2].charAt(0) == 'g' || mat[i + 2][j - 2].charAt(0) == 'G' && mat[i + 2][j - 2].contains(color));
                            diagonalArribaAbajoDerechaIzquierda = (mat[i + 1][j - 1].charAt(0) == 'g' || mat[i + 1][j - 1].charAt(0) == 'G') && mat[i + 1][j - 1].contains(color) && (mat[i + 2][j - 2].charAt(0) == 'g' || mat[i + 2][j - 2].charAt(0) == 'G') && mat[i + 2][j - 2].contains(color);
                        }
                    }
                    //diagonalArribaAbajoIzquierdaDerecha = mat[i + 1][j + 1].charAt(0) == 'g' && mat[i + 1][j + 1].contains(color) && (mat[i + 2][j + 2].charAt(0) == 'g' && mat[i + 2][j + 2].contains(color));
                    //diagonalArribaAbajoDerechaIzquierda = mat[i + 1][j - 1].charAt(0) == 'g' && mat[i + 1][j - 1].contains(color) && (mat[i + 2][j - 2].charAt(0) == 'g' && mat[i + 2][j - 2].contains(color));
                    //arriba a la izquierda pronto
                    if (i == 0 && j == 0) {
                        if (horizontal) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoIzquierdaDerecha) {
                            hayGanador = true;
                        }
                        if (vertical) {
                            hayGanador = true;
                        }
                    } else if (i == 0 && j == 1) {
                        // borde techo iz que no es pared
                        if (horizontal) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoIzquierdaDerecha) {
                            hayGanador = true;
                        }
                        if (vertical) {
                            hayGanador = true;
                        }
                    } else if (i == 0 && (j == 2 || j == 3)) {
                        //Techo 2 del medio
                        if (horizontal) {
                            hayGanador = true;
                        }
                        if (vertical) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoIzquierdaDerecha) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoDerechaIzquierda) {
                            hayGanador = true;
                        }
                    } else if (i == 0 && i == 4) {
                        //Techo derecha que no es pared
                        if (vertical) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoDerechaIzquierda) {
                            hayGanador = true;
                        }
                    } else if ((i == 0 && j == 5) || (i == 1 && j == 5)) {
                        // Esquina arriba derecha Y pared arriba derecha que no es techo
                        if (vertical) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoDerechaIzquierda) {
                            hayGanador = true;
                        }
                    } else if ((i == 2 || i == 3) && j == 5) {
                        //Pared derecha arriba que no es techo
                        if (vertical) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoDerechaIzquierda) {
                            hayGanador = true;
                        }
                    } else if (i == 5 && (j == 2 || j == 3)) {
                        //piso en el medio
                        if (horizontal) {
                            hayGanador = true;
                        }
                    } else if ((i == 5 && j == 1) || (i == 5 && j == 0) || (i == 4 && j == 1)) {
                        // Abajo izqueirda que no es pared Y esquina abajo izquierda Y pared abajo que no es piso
                        if (horizontal) {
                            hayGanador = true;
                        }
                    } else if (((i == 2 || i == 3) && j == 0) || (i == 1 && j == 0)) {
                        //Pared izquierda en el medio Y pared izquierda arriba que no es techo
                        if (horizontal) {
                            hayGanador = true;
                        }
                        if (diagonalArribaAbajoIzquierdaDerecha) {
                            hayGanador = true;
                        }
                        if (vertical) {
                            hayGanador = true;
                        }
                    } else {
                        //cualquiera del medio
                        if ((i == 1 || i == 2) && (j == 2 || j == 3)) {
                            //los 4 del medio uno antes del techo 
                            if (vertical) {
                                hayGanador = true;
                            }
                            if (horizontal) {
                                hayGanador = true;
                            }
                            if (diagonalArribaAbajoDerechaIzquierda) {
                                hayGanador = true;
                            }
                            if (diagonalArribaAbajoIzquierdaDerecha) {
                                hayGanador = true;
                            }
                        }
                        if ((i == 1 || i == 2) && j == 1) {
                            // los 2 del medio arriba a la izquierda uno arriba del otro
                            if (vertical) {
                                hayGanador = true;
                            }
                            if (horizontal) {
                                hayGanador = true;
                            }
                            if (diagonalArribaAbajoIzquierdaDerecha) {
                                hayGanador = true;
                            }
                        }
                        if ((i == 1 || i == 2) && j == 4) {
                            //los 2 del medio arriba a la derecha uno arriba del otro
                            if (vertical) {
                                hayGanador = true;
                            }
                            if (diagonalArribaAbajoDerechaIzquierda) {
                                hayGanador = true;
                            }
                        }
                        if (i == 3 && (j == 1 || j == 4)) {
                            //los dos de abajo (fila 3) en cada punta
                            if (vertical) {
                                hayGanador = true;
                            }

                        }
                        if (i == 3 && (j == 2 || j == 3)) {
                            //dos del medio abajo(fila 3)
                            if (horizontal) {
                                hayGanador = true;
                            }
                        }
                        if (i == 5 && (j == 2 || j == 3)) {
                            //los dos del medio abajo(fila 4)
                            if (horizontal) {
                                hayGanador = true;
                            }
                        }
                    }
                }
                if (hayGanador) {
                    if (horizontal) {
                        coordenadas = (i + "" + j + " " + i + (j + 1) + " " + i + (j + 2));
                    }
                    if (vertical) {
                        coordenadas = (i + "" + j + " " + (i + 1) + j + " " + (i + 2) + j);
                    }
                    if (diagonalArribaAbajoDerechaIzquierda) {
                        coordenadas = (i + "" + j + " " + (i + 1) + (j - 1) + " " + (i + 2) + (j - 2));
                    }
                    if (diagonalArribaAbajoIzquierdaDerecha) {
                        coordenadas = (i + "" + j + " " + (i + 1) + (j + 1) + " " + (i + 2) + (j + 2));
                    }
                }

            }
        }

        if (!hayGanador) {
            color = "";
        }
        return color + " " + coordenadas;
    }

    public static int tieneGatoGrande(String[][] mat, String[] coordenadas) {
        int contadorGatosGrandes = 0;
        for (int i = 0; i < coordenadas.length; i++) {
            if (mat[Character.getNumericValue(coordenadas[i].charAt(0))][Character.getNumericValue(coordenadas[i].charAt(1))].charAt(0) == 'G') {
                contadorGatosGrandes++;
            }
        }
        return contadorGatosGrandes;
    }

    public static Boolean todoGatosGrandes(String[][] mat, String color) {
        Boolean gano = false;
        int gatos = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j].charAt(0) == 'G') {
                    if (mat[i][j].contains(color)) {
                        gatos++;
                    }
                }
                if (gatos == 8) {
                    gano = true;
                }
            }
        }
        return gano;
    }

}


// FALTA METER TODO EL CODIGO EN UN WHILE QUE HAGA QUE MIENTRAS NO SE PRESIONA LA "E" SIGA TRIGGEREANDO EL MENU. 
