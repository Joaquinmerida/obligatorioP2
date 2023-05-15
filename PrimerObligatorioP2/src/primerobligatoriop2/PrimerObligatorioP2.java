package primerobligatoriop2;

import java.util.*;
import primerobligatoriop2.Utilidades.*;
import javax.sound.sampled.*;
import java.io.IOException;

public class PrimerObligatorioP2 {

    public static void main(String[] args) {
        Boolean saleDelSistema = false;
        ArrayList<Perfiles> personas = new ArrayList<>();
        ArrayList<Perfiles> personasAuxiliar = new ArrayList<>();
        char seleccionGato = 0;

        while (!saleDelSistema) {
            String ingresoMenu = "";
            Scanner input = new Scanner(System.in);
            ingresoMenu = mostrarMenu();
            Random random = new Random();
            int seleccionEstilo = random.nextInt(5) + 1;
            String ganador = "";
            String saltoGatitos = "";
            int seleccionJugador = 0;
            Boolean seRindio = false;
            String ganadorPorAbandono = "";
            int contadorTurnos = 0;
            String[][] mat = new String[6][6];
            int[] cajaJugadores = {8, 0, 8, 0};
            boolean finJuego = false;
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    mat[i][j] = " ";
                }
            }

            if (ingresoMenu.charAt(0) == 'R') {
                Perfiles.registrarJugador(personas);
            }

            if (ingresoMenu.charAt(0) == 'D') {
                try {
                    GeneradorReportes.GenerarPDF(personas);
                } catch (Exception e) {
                    System.out.println("No se pudo generar el pdf.");
                }
            }

            if (ingresoMenu.charAt(0) == 'S') {
                if (personas.size() >= 2) {
                    for (int i = 0; i < personas.size(); i++) {
                        System.out.println(i + " - " + personas.get(i).getNombre());
                    }
                    System.out.println("Jugador rojo, seleccione su perfil:");
                    seleccionJugador = input.nextInt();
                    while (!(seleccionJugador >= 0 && seleccionJugador < personas.size())) {
                        System.out.println("Jugador rojo, seleccione su perfil");
                        seleccionJugador = input.nextInt();
                    }
                    Perfiles.sumarPartida(personas, personas.get(seleccionJugador).getAlias());
                    personasAuxiliar.clear();
                    personasAuxiliar.addAll(personas);
                    personasAuxiliar.remove(seleccionJugador);
                    for (int i = 0; i < personasAuxiliar.size(); i++) {
                        System.out.println(i + " - " + personasAuxiliar.get(i).getNombre());
                    }
                    System.out.println("Jugador azul, seleccione su perfil:");
                    seleccionJugador = input.nextInt();

                    while (!(seleccionJugador >= 0 && seleccionJugador < personasAuxiliar.size())) {
                        System.out.println("Jugador azul, seleccione su perfil");
                        seleccionJugador = input.nextInt();
                    }
                    Perfiles.sumarPartida(personasAuxiliar, personasAuxiliar.get(seleccionJugador).getAlias());

                    estilosMatriz(seleccionEstilo, mat, cajaJugadores);

                    while (!finJuego) {
                        if (contadorTurnos % 2 == 0) {
                            //TURNO DE ROJO
                            System.out.println("Jugador Rojo: Coloque las coordenadas del nuevo gatito");
                            String coordenadasInput = input.next();
                            if (coordenadasInput.charAt(0) == 'x' || coordenadasInput.charAt(0) == 'X') {
                                finJuego = true;
                                System.out.println("Gano azul por abandono del jugador rojo");
                                break;
                            }
                            String coordenadaX = coordenadasInput.substring(0, 1);
                            int coordenadaY = Character.getNumericValue(coordenadasInput.charAt(1));
                            int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            String letrasCheck = "ABCDEFabcdef";
                            Boolean xBien = letrasCheck.contains(coordenadaX);
                            Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                            while (!(xBien && yBien)) {
                                System.out.println("Coordenadas incorrectas");
                                System.out.println("Jugador Rojo: Coloque las coordenadas del nuevo gatito");
                                coordenadasInput = input.next();
                                if (coordenadasInput.charAt(0) == 'x' || coordenadasInput.charAt(0) == 'X') {
                                    finJuego = true;
                                    System.out.println("Gano rojo por abandono del jugador azul");
                                    break;
                                }
                                coordenadaX = coordenadasInput.substring(0, 1);
                                coordenadaY = Character.getNumericValue(coordenadasInput.charAt(1));
                                xBien = letrasCheck.contains(coordenadaX);
                                yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                                coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            }
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                                System.out.println("Ya hay un gatito en esa casilla, vuelva a ingresar las coordenadas del nuevo gatito");
                                coordenadasInput = input.next();
                                coordenadaX = coordenadasInput.substring(0, 1);
                                coordenadaY = Character.getNumericValue(coordenadasInput.charAt(1));
                                coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            }
                            MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "rojo");
                            MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                            saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");

                            if (saltoGatitos.length() > 0) {
                                try {
                                    Sonidos.reproducirSonido();
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                    e.printStackTrace();
                                }
                            }
                            MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                            cajaJugadores[0]--;
                        } else {
                            //TURNO DE AZUL
                            System.out.println("Jugador Azul: Coloque las coordenadas del nuevo gatito");
                            String coordenadasInput = input.next();
                            if (coordenadasInput.charAt(0) == 'x' || coordenadasInput.charAt(0) == 'X') {
                                finJuego = true;
                                System.out.println("Gano rojo por abandono del jugador azul");
                                break;
                            }
                            String coordenadaX = coordenadasInput.substring(0, 1);
                            int coordenadaY = Character.getNumericValue(coordenadasInput.charAt(1));
                            int[] coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            String letrasCheck = "ABCDEFabcdef";
                            Boolean xBien = letrasCheck.contains(coordenadaX);
                            Boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                            while (!(xBien && yBien)) {
                                System.out.println("Coordenadas incorrectas");
                                System.out.println("Jugador Azul: Coloque las coordenadas del nuevo gatito");
                                coordenadasInput = input.next();
                                if (coordenadasInput.charAt(0) == 'x' || coordenadasInput.charAt(0) == 'X') {
                                    finJuego = true;
                                    System.out.println("Gano rojo por abandono del jugador azul");
                                    break;
                                }
                                coordenadaX = coordenadasInput.substring(0, 1);
                                coordenadaY = Character.getNumericValue(coordenadasInput.charAt(1));
                                coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                                xBien = letrasCheck.contains(coordenadaX);
                                yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                            }
                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            while (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat)) {
                                System.out.println("Ya hay un gatito en esa casilla, vuelva a ingresar las coordenadas del nuevo gatito");
                                coordenadasInput = input.next();
                                if (coordenadasInput.charAt(0) == 'x' || coordenadasInput.charAt(0) == 'X') {
                                    finJuego = true;
                                    System.out.println("Gano rojo por abandono del jugador azul");
                                    break;
                                }
                                coordenadaX = coordenadasInput.substring(0, 1);
                                coordenadaY = Character.getNumericValue(coordenadasInput.charAt(1));
                                coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                            }
                            MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "azul");
                            MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                            saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");

                            if (saltoGatitos.length() > 0) {
                                try {
                                    Sonidos.reproducirSonido();
                                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                    e.printStackTrace();
                                }
                            }
                            MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                            cajaJugadores[2]--;
                        }

                        //RENDER DE LA MATRIZ SEGUN COLOR
                        estilosMatriz(seleccionEstilo, mat, cajaJugadores);
                        contadorTurnos++;

                        if (hayGanador(mat).length() > 1) {
                            System.out.println("Felicitaciones jugador " + hayGanador(mat).substring(0, 4) + " sos el ganador!!");
                            finJuego = true;
                        }

                    }
                } else {
                    System.out.println("No hay suficientes jugadores registrados");
                }
            }

            if (ingresoMenu.charAt(0) == 'F') {
                //MODO FULL
                String inputJugador = "";
                finJuego = false;

                if (personas.size() >= 2) {
                    for (int i = 0; i < personas.size(); i++) {
                        System.out.println(i + " - " + personas.get(i).getNombre());
                    }
                    System.out.println("Jugador Rojo, seleccione su perfil:");
                    seleccionJugador = input.nextInt();
                    while (!(seleccionJugador >= 0 && seleccionJugador < personas.size())) {
                        System.out.println("Jugador Rojo, seleccione su perfil");
                        seleccionJugador = input.nextInt();
                    }
                    Perfiles.sumarPartida(personas, personas.get(seleccionJugador).getAlias());
                    personasAuxiliar.clear();
                    personasAuxiliar.addAll(personas);
                    personasAuxiliar.remove(seleccionJugador);
                    for (int i = 0; i < personasAuxiliar.size(); i++) {
                        System.out.println(i + " - " + personasAuxiliar.get(i).getNombre());
                    }
                    System.out.println("Jugador Azul, seleccione su perfil:");
                    seleccionJugador = input.nextInt();

                    while (!(seleccionJugador >= 0 && seleccionJugador < personasAuxiliar.size())) {
                        System.out.println("Jugador Azul, seleccione su perfil");
                        seleccionJugador = input.nextInt();
                    }
                    Perfiles.sumarPartida(personasAuxiliar, personasAuxiliar.get(seleccionJugador).getAlias());

                    estilosMatriz(seleccionEstilo, mat, cajaJugadores);

                    while (!finJuego) {
                        if (contadorTurnos % 2 == 0) {
                            int[] coordenadas = new int[2];
                            //TURNO DE ROJO
                            if (cajaJugadores[1] > 0) {
                                // SI TIENE GATO GRANDE         
                                System.out.println("Jugador Rojo: Coloque las coordenadas del nuevo gato");
                                String coordenadaX;
                                int coordenadaY;
                                String letrasCheck = "ABCDEFabcdef";
                                boolean coordenadasValidas = false;
                                boolean coordenadasDisponibles = false;
                                do {
                                    String inputt = input.next();
                                    if (inputt.charAt(0) == 'x' || inputt.charAt(0) == 'X') {
                                        finJuego = true;
                                        System.out.println("Gano azul por abandono del jugador rojo");
                                        break;
                                    }
                                    if (inputt.length() == 3) {
                                        coordenadaX = String.valueOf(inputt.charAt(0));
                                        coordenadaY = Character.getNumericValue(inputt.charAt(1));
                                        seleccionGato = inputt.charAt(2);
                                        boolean xBien = letrasCheck.contains(coordenadaX);
                                        boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                                        boolean gBien = seleccionGato == 'g' || seleccionGato == 'G';
                                        if (xBien && yBien && gBien) {
                                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                                            coordenadasValidas = true;
                                            if (!MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && !MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat)) {
                                                coordenadasDisponibles = true;
                                            } else {
                                                System.out.println("Ya hay un gatito en esa casilla, vuelva a ingresar las coordenadas del nuevo gatito");
                                            }
                                        } else {
                                            System.out.println("Coordenadas incorrectas");
                                        }
                                    } else {
                                        System.out.println("Coordenadas incorrectas");
                                    }
                                } while (!coordenadasValidas || !coordenadasDisponibles);
                            } else {
                                //SI NO TIENE GATO GRANDE
                                System.out.println("Jugador Rojo: Coloque las coordenadas del nuevo gatito");
                                String coordenadaX;
                                int coordenadaY;
                                String letrasCheck = "ABCDEFabcdef";
                                boolean coordenadasValidas = false;
                                boolean coordenadasDisponibles = false;
                                do {
                                    String inputt = input.next();
                                    if (inputt.charAt(0) == 'x' || inputt.charAt(0) == 'X') {
                                        finJuego = true;
                                        System.out.println("Gano azul por abandono del jugador rojo");
                                        break;
                                    }
                                    if (inputt.length() == 3) {
                                        coordenadaX = String.valueOf(inputt.charAt(0));
                                        coordenadaY = Character.getNumericValue(inputt.charAt(1));
                                        seleccionGato = inputt.charAt(2);
                                        boolean xBien = letrasCheck.contains(coordenadaX);
                                        boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                                        boolean gBien = seleccionGato == 'g';
                                        if (xBien && yBien && gBien) {
                                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                                            coordenadasValidas = true;
                                            if (!MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && !MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat)) {
                                                coordenadasDisponibles = true;
                                            } else {
                                                System.out.println("Ya hay un gatito en esa casilla, vuelva a ingresar las coordenadas del nuevo gatito");
                                            }
                                        } else {
                                            System.out.println("Coordenadas incorrectas");
                                        }
                                    } else {
                                        System.out.println("Coordenadas incorrectas");
                                    }
                                } while (!coordenadasValidas || !coordenadasDisponibles);
                            }

                            if (seleccionGato == 'g') {
                                if (cajaJugadores[0] > 0) {
                                    MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "rojo");
                                    saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                                    MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                                    if (saltoGatitos.length() > 0) {
                                        try {
                                            Sonidos.reproducirSonido();
                                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    cajaJugadores[0]--;
                                    contadorTurnos++;
                                    estilosMatriz(seleccionEstilo, mat, cajaJugadores);
                                } else {
                                    System.out.println("No tienes mas gatitos!");
                                }
                            }
                            if (seleccionGato == 'G') {
                                if (cajaJugadores[1] > 0) {
                                    MovimientoGatitos.colocarGato(coordenadas[0], coordenadas[1], mat, "rojo");
                                    saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "G");
                                    if (saltoGatitos.length() > 0) {
                                        try {
                                            Sonidos.reproducirSonido();
                                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                                    cajaJugadores[1]--;
                                    contadorTurnos++;
                                } else {
                                    System.out.println("No tienes mas gatos!");
                                }
                            }

                            Boolean finalPor8Gatos = false;
                            if (cajaJugadores[2] == 0 && cajaJugadores[3] == 0) {
                                finalPor8Gatos = todoGatosGrandes(mat, "azul");
                                if (!finalPor8Gatos) {
                                    Boolean jugadaCorrecta = false;
                                    while (!jugadaCorrecta) {
                                        System.out.println("Jugador Azul: te has quedado sin gatitos ni gatos. Solo puedes usar las reglas adicionales");
                                        System.out.println("Ingresa RA1 o RA2 para utilizar las reglas adicionales");
                                        System.out.println("RA1 + coordenadas = Quitar un gatito de la colcha y convertirlo en gato");
                                        System.out.println("RA2 + coordenadas= Quitar un gato de la colcha y guardarlo en la caja");
                                        String inputReglasAdicionales = input.next();
                                        String letrasCheck = "ABCDEFabcdef";

                                        Boolean xBien = letrasCheck.contains(inputReglasAdicionales.substring(3, 4));
                                        Boolean yBien = (Character.getNumericValue(inputReglasAdicionales.charAt(4)) >= 0 && Character.getNumericValue(inputReglasAdicionales.charAt(4)) <= 5);

                                        if (xBien && yBien) {
                                            coordenadas = MovimientoGatitos.getCoordenadas(inputReglasAdicionales.substring(3, 4), Character.getNumericValue(inputReglasAdicionales.charAt(4)));
                                            if (inputReglasAdicionales.contains("RA1") && inputReglasAdicionales.length() == 5) {
                                                if (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("azul")) {
                                                    MovimientoGatitos.sacaConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                                    cajaJugadores[3]++;
                                                    jugadaCorrecta = true;
                                                    break;
                                                } else {
                                                    System.out.println("No hay gatito en esas cordenadas");
                                                }

                                            } else {
                                                System.out.println("Recuerda que solo puede usar las reglas adicionales!");
                                            }
                                            if (inputReglasAdicionales.contains("RA2")) {
                                                if (MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("azul")) {
                                                    MovimientoGatitos.sacaConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                                    cajaJugadores[3]++;
                                                    jugadaCorrecta = true;
                                                    continue;
                                                } else {
                                                    System.out.println("No hay un gato en esa posicion");
                                                }
                                            }
                                        } else {
                                            System.out.println("coordenadas no validas");
                                        }
                                    }
                                } else {
                                    System.out.println("Gano jugador azul porque tiene 8 gatos en la colcha");
                                    finJuego = true;
                                }
                            }
                            inputJugador = "";
                        } else {
                            int[] coordenadas = new int[2];
                            //TURNO DE AZUL
                            if (cajaJugadores[3] > 0) {
                                // SI TIENE GATO GRANDE         
                                System.out.println("Jugador Azul: Coloque las coordenadas del nuevo gatito");
                                String coordenadaX;
                                int coordenadaY;
                                String letrasCheck = "ABCDEFabcdef";
                                boolean coordenadasValidas = false;
                                boolean coordenadasDisponibles = false;
                                do {
                                    String inputt = input.next();
                                    if (inputt.charAt(0) == 'x' || inputt.charAt(0) == 'X') {
                                        finJuego = true;
                                        System.out.println("Gano rojo por abandono del jugador azul");
                                        break;
                                    }
                                    if (inputt.length() == 3) {
                                        coordenadaX = String.valueOf(inputt.charAt(0));
                                        coordenadaY = Character.getNumericValue(inputt.charAt(1));
                                        seleccionGato = inputt.charAt(2);
                                        boolean xBien = letrasCheck.contains(coordenadaX);
                                        boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                                        boolean gBien = seleccionGato == 'g' || seleccionGato == 'G';
                                        if (xBien && yBien && gBien) {
                                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                                            coordenadasValidas = true;
                                            if (!MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && !MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat)) {
                                                coordenadasDisponibles = true;
                                            } else {
                                                System.out.println("Ya hay un gatito en esa casilla, vuelva a ingresar las coordenadas del nuevo gatito");
                                            }
                                        } else {
                                            System.out.println("Coordenadas incorrectas1");
                                        }
                                    } else {
                                        System.out.println("Coordenadas incorrectas2");
                                    }
                                } while (!coordenadasValidas || !coordenadasDisponibles);
                            } else {
                                //SI NO TIENE GATO GRANDE    
                                System.out.println("Jugador Azul: Coloque las coordenadas del nuevo gatito");
                                String coordenadaX;
                                int coordenadaY;
                                String letrasCheck = "ABCDEFabcdef";
                                boolean coordenadasValidas = false;
                                boolean coordenadasDisponibles = false;
                                do {
                                    String inputt = input.next();
                                    if (inputt.charAt(0) == 'x' || inputt.charAt(0) == 'X') {
                                        finJuego = true;
                                        System.out.println("Gano rojo por abandono del jugador azul");
                                        break;
                                    }
                                    if (inputt.length() == 3) {
                                        coordenadaX = String.valueOf(inputt.charAt(0));
                                        coordenadaY = Character.getNumericValue(inputt.charAt(1));
                                        seleccionGato = inputt.charAt(2);
                                        boolean xBien = letrasCheck.contains(coordenadaX);
                                        boolean yBien = (coordenadaY >= 0 && coordenadaY <= 5);
                                        boolean gBien = seleccionGato == 'g';
                                        if (xBien && yBien && gBien) {
                                            coordenadas = MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                                            coordenadasValidas = true;
                                            if (!MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && !MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat)) {
                                                coordenadasDisponibles = true;
                                            } else {
                                                System.out.println("Ya hay un gatito en esa casilla, vuelva a ingresar las coordenadas del nuevo gatito");
                                            }
                                        } else {
                                            if (seleccionGato == 'G') {
                                                System.out.println("No tienes ningun gato en la caja");
                                            } else {
                                                System.out.println("Coordenadas incorrectas1");
                                            }
                                        }
                                    } else {
                                        System.out.println("Coordenadas incorrectas2");
                                    }
                                } while (!coordenadasValidas || !coordenadasDisponibles);
                            }

                            if (seleccionGato == 'g') {
                                if (cajaJugadores[2] > 0) {
                                    MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "azul");
                                    saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "g");
                                    MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                                    cajaJugadores[2]--;
                                    contadorTurnos++;
                                    if (saltoGatitos.length() > 0) {
                                        try {
                                            Sonidos.reproducirSonido();
                                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    System.out.println("No tienes mas gatitos");
                                }

                            }

                            if (seleccionGato == 'G') {
                                if (cajaJugadores[3] > 0) {
                                    MovimientoGatitos.colocarGato(coordenadas[0], coordenadas[1], mat, "azul");
                                    saltoGatitos = MovimientoGatitos.seMueve(coordenadas[0], coordenadas[1], mat, "G");
                                    MovimientoGatitos.saltoGato(saltoGatitos, coordenadas[0], coordenadas[1], mat, cajaJugadores);
                                    if (saltoGatitos.length() > 0) {
                                        try {
                                            Sonidos.reproducirSonido();
                                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    cajaJugadores[3]--;
                                    contadorTurnos++;

                                } else {
                                    System.out.println("Jugador Azul: No tienes gatos en tu caja");
                                }

                            }

                            Boolean finalPor8Gatos = false;

                            if (cajaJugadores[0] == 0 && cajaJugadores[1] == 0) {
                                finalPor8Gatos = todoGatosGrandes(mat, "rojo");
                                if (!finalPor8Gatos) {
                                    Boolean jugadaCorrecta = false;
                                    while (!jugadaCorrecta) {
                                        estilosMatriz(seleccionEstilo, mat, cajaJugadores);
                                        System.out.println("Jugar Rojo: te has quedado sin gatitos ni gatos. Solo puedes usar las reglas adicionales");
                                        System.out.println("Ingresa RA1 o RA2 para utilizar las reglas adicionales");
                                        System.out.println("RA1 + coordenadas = Quitar un gatito de la colcha y convertirlo en gato");
                                        System.out.println("RA2 + coordenadas= Quitar un gato de la colcha y guardarlo en la caja");
                                        estilosMatriz(seleccionEstilo, mat, cajaJugadores);
                                        String inputReglasAdicionales = input.next();
                                        String letrasCheck = "ABCDEFabcdef";
                                        Boolean xBien = letrasCheck.contains(inputReglasAdicionales.substring(3, 4));
                                        Boolean yBien = (Character.getNumericValue(inputReglasAdicionales.charAt(4)) >= 0 && Character.getNumericValue(inputReglasAdicionales.charAt(4)) <= 5);
                                        if (xBien && yBien) {
                                            while (inputReglasAdicionales.contains("RA1") || inputReglasAdicionales.contains("RA2") && Character.isDigit(inputReglasAdicionales.charAt(3))) {
                                                coordenadas = MovimientoGatitos.getCoordenadas(inputReglasAdicionales.substring(3, 4), Character.getNumericValue(inputReglasAdicionales.charAt(4)));
                                                if (inputReglasAdicionales.contains("RA1") && inputReglasAdicionales.length() == 5) {
                                                    if (MovimientoGatitos.yaHayGatito(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("rojo")) {
                                                        MovimientoGatitos.sacaConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                                        cajaJugadores[1]++;
                                                        jugadaCorrecta = true;
                                                        break;
                                                    } else {
                                                        System.out.println("No hay gatito en esas cordenadas");
                                                    }

                                                } else {
                                                    System.out.println("Recuerda que solo puede usar las reglas adicionales!");
                                                }
                                                if (inputReglasAdicionales.contains("RA2")) {
                                                    if (MovimientoGatitos.yaHayGato(coordenadas[0], coordenadas[1], mat) && mat[coordenadas[0]][coordenadas[1]].contains("rojo")) {
                                                        MovimientoGatitos.sacaConCoordenadas(mat, coordenadas[0], coordenadas[1]);
                                                        cajaJugadores[1]++;
                                                        jugadaCorrecta = true;
                                                    } else {
                                                        System.out.println("No hay un gato en esa posicion");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Gano jugador Rojo porque tiene 8 gatos en la colcha");
                                    finJuego = true;
                                }
                            }
                            inputJugador = "";
                        }

                        if (hayGanador(mat).length() > 5) {
                            String color = hayGanador(mat).substring(0, 4);
                            String datosGanador = hayGanador(mat).substring(5);
                            String[] coordenadasGanador = datosGanador.split(" ");
                            System.out.println(tieneGatoGrande(mat, coordenadasGanador));
                            MovimientoGatitos.agregaGatoACaja(color, cajaJugadores, 3);
                            Boolean ganoFull = tieneGatoGrande(mat, coordenadasGanador) == 3;
                            MovimientoGatitos.desaparecerGatitos(mat, coordenadasGanador);
                            if (ganoFull) {
                                System.out.println(color + " gano el juego!!");
                                finJuego = true;
                            }
                        }
                        estilosMatriz(seleccionEstilo, mat, cajaJugadores);
                    }
                } else {
                    System.out.println("No hay suficientes jugadores registrados");
                }
            }
            //termina todo sale del menu
            if (ingresoMenu.charAt(0) == 'E') {
                saleDelSistema = true;
            }
            ingresoMenu = "";
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

    public static String mostrarMenu() {
        System.out.println("Presiona R para registrar jugador");
        System.out.println("Presiona S para jugar modo Simple");
        System.out.println("Presiona F para jugar modo Full");
        System.out.println("Presiona D para generar un report de juego");
        System.out.println("Presiona E para salir del juego");
        Scanner input = new Scanner(System.in);
        String ingresoMenu = input.next();
        return ingresoMenu;
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

    public static void estilosMatriz(int numeroDeEstilo, String[][] mat, int[] cajaJugadores) {
        String numeroALetra = "";

        if (numeroDeEstilo == 1) {
            for (int i = 0; i < mat.length; i++) {
                if (i == 0) {
                    numeroALetra = "A";
                }
                if (i == 1) {
                    numeroALetra = "B";
                }
                if (i == 2) {
                    numeroALetra = "C";
                }
                if (i == 3) {
                    numeroALetra = "D";
                }
                if (i == 4) {
                    numeroALetra = "E";
                }
                if (i == 5) {
                    numeroALetra = "F";
                }

                if (i == 0) {
                    System.out.println("       0       1       2       3       4       5 ");
                }
                System.out.println("   +-------+-------+-------+-------+-------+-------+");
                System.out.println("   |       |       |       |       |       |       |");
                for (int j = 0; j < mat[0].length; j++) {
                    if (mat[i][j].contains("rojo")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  |  " + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + "   ");
                        } else {
                            System.out.print("|  " + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + "   ");
                        }
                    } else if (mat[i][j].contains("azul")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  |  " + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + "   ");
                        } else {
                            System.out.print("\u001B[0m" + "|  " + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + "   ");
                        }
                    } else {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  |       ");
                        } else {
                            System.out.print("\u001B[0m" + "|       ");
                        }
                    }
                    if (j == (mat[0].length - 1)) {
                        System.out.println("|");
                    }
                }
                System.out.println("   |       |       |       |       |       |       |");

            }
            System.out.println("   +-------+-------+-------+-------+-------+-------+");
        }

        if (numeroDeEstilo == 2) {
            for (int i = 0; i < mat.length; i++) {
                if (i == 0) {
                    numeroALetra = "A";
                }
                if (i == 1) {
                    numeroALetra = "B";
                }
                if (i == 2) {
                    numeroALetra = "C";
                }
                if (i == 3) {
                    numeroALetra = "D";
                }
                if (i == 4) {
                    numeroALetra = "E";
                }
                if (i == 5) {
                    numeroALetra = "F";
                }

                if (i == 0) {
                    System.out.println("       0       1       2       3       4       5 ");
                }
                System.out.println("   +~~~~~~~+~~~~~~~+~~~~~~~+~~~~~~~+~~~~~~~+~~~~~~~+");
                System.out.println("   | # # # | # # # | # # # | # # # | # # # | # # # |");
                for (int j = 0; j < mat[0].length; j++) {

                    if (mat[i][j].contains("rojo")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | #" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " # ");
                        } else {
                            System.out.print("| #" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " # ");
                        }
                    } else if (mat[i][j].contains("azul")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | #" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " # ");
                        } else {
                            System.out.print("\u001B[0m" + "| #" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " # ");
                        }
                    } else {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | #   # ");
                        } else {
                            System.out.print("\u001B[0m" + "| #   # ");
                        }
                    }
                    if (j == (mat[0].length - 1)) {
                        System.out.println("|");
                    }
                }
                System.out.println("   | # # # | # # # | # # # | # # # | # # # | # # # |");
            }
            System.out.println("   +~~~~~~~+~~~~~~~+~~~~~~~+~~~~~~~+~~~~~~~+~~~~~~~+");
        }

        if (numeroDeEstilo == 3) {
            for (int i = 0; i < mat.length; i++) {
                if (i == 0) {
                    numeroALetra = "A";
                }
                if (i == 1) {
                    numeroALetra = "B";
                }
                if (i == 2) {
                    numeroALetra = "C";
                }
                if (i == 3) {
                    numeroALetra = "D";
                }
                if (i == 4) {
                    numeroALetra = "E";
                }
                if (i == 5) {
                    numeroALetra = "F";
                }

                if (i == 0) {
                    System.out.println("       0       1       2       3       4       5 ");
                }
                System.out.println("   +-------+-------+-------+-------+-------+-------+");
                System.out.println("   | * * * | * * * | * * * | * * * | * * * | * * * |");
                for (int j = 0; j < mat[0].length; j++) {

                    if (mat[i][j].contains("rojo")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | *" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " * ");
                        } else {
                            System.out.print("| *" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " * ");
                        }
                    } else if (mat[i][j].contains("azul")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | *" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " * ");
                        } else {
                            System.out.print("\u001B[0m" + "| *" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " * ");
                        }
                    } else {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | *   * ");
                        } else {
                            System.out.print("\u001B[0m" + "| *   * ");
                        }
                    }
                    if (j == (mat[0].length - 1)) {
                        System.out.println("|");
                    }
                }
                System.out.println("   | * * * | * * * | * * * | * * * | * * * | * * * |");
            }
            System.out.println("   +-------+-------+-------+-------+-------+-------+");
        }

        if (numeroDeEstilo == 4) {
            for (int i = 0; i < mat.length; i++) {
                if (i == 0) {
                    numeroALetra = "A";
                }
                if (i == 1) {
                    numeroALetra = "B";
                }
                if (i == 2) {
                    numeroALetra = "C";
                }
                if (i == 3) {
                    numeroALetra = "D";
                }
                if (i == 4) {
                    numeroALetra = "E";
                }
                if (i == 5) {
                    numeroALetra = "F";
                }

                if (i == 0) {
                    System.out.println("       0       1       2       3       4       5 ");
                }
                System.out.println("   +-------+-------+-------+-------+-------+-------+");
                System.out.println("   | « « « | « « « | « « « | « « « | « « « | « « « |");
                for (int j = 0; j < mat[0].length; j++) {

                    if (mat[i][j].contains("rojo")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | «" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " * ");
                        } else {
                            System.out.print("| «" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " « ");
                        }
                    } else if (mat[i][j].contains("azul")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | «" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " « ");
                        } else {
                            System.out.print("\u001B[0m" + "| «" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " « ");
                        }
                    } else {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | «   « ");
                        } else {
                            System.out.print("\u001B[0m" + "| «   « ");
                        }
                    }
                    if (j == (mat[0].length - 1)) {
                        System.out.println("|");
                    }
                }
                System.out.println("   | « « « | « « « | « « « | « « « | « « « | « « « |");
            }
            System.out.println("   +-------+-------+-------+-------+-------+-------+");
        }

        if (numeroDeEstilo == 5) {
            for (int i = 0; i < mat.length; i++) {
                if (i == 0) {
                    numeroALetra = "A";
                }
                if (i == 1) {
                    numeroALetra = "B";
                }
                if (i == 2) {
                    numeroALetra = "C";
                }
                if (i == 3) {
                    numeroALetra = "D";
                }
                if (i == 4) {
                    numeroALetra = "E";
                }
                if (i == 5) {
                    numeroALetra = "F";
                }

                if (i == 0) {
                    System.out.println("       0       1       2       3       4       5 ");
                }
                System.out.println("   +-------+-------+-------+-------+-------+-------+");
                System.out.println("   | $ $ $ | $ $ $ | $ $ $ | $ $ $ | $ $ $ | $ $ $ |");
                for (int j = 0; j < mat[0].length; j++) {

                    if (mat[i][j].contains("rojo")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | $" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " $ ");
                        } else {
                            System.out.print("| $" + "\u001B[31m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " $ ");
                        }
                    } else if (mat[i][j].contains("azul")) {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | $" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " $ ");
                        } else {
                            System.out.print("\u001B[0m" + "| $" + "\u001B[34m" + " " + mat[i][j].charAt(0) + "\u001B[0m" + " $ ");
                        }
                    } else {
                        if (j == 0) {
                            System.out.print("\u001B[0m" + numeroALetra + "\u001B[0m" + "  | $   $ ");
                        } else {
                            System.out.print("\u001B[0m" + "| $   $ ");
                        }
                    }
                    if (j == (mat[0].length - 1)) {
                        System.out.println("|");
                    }
                }
                System.out.println("   | $ $ $ | $ $ $ | $ $ $ | $ $ $ | $ $ $ | $ $ $ |");
            }
            System.out.println("   +-------+-------+-------+-------+-------+-------+");
        }

        System.out.println("\u001B[31m" + "GATITOS RESTANTES: " + cajaJugadores[0]);
        System.out.println("\u001B[34m" + "GATITOS RESTANTES: " + cajaJugadores[2]);
        System.out.println("\u001B[31m" + "GATOS RESTANTES: " + cajaJugadores[1]);
        System.out.println("\u001B[34m" + "GATOS RESTANTES: " + cajaJugadores[3]);
    }
}
