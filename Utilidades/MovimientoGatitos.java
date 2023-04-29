/*

 */
package primerobligatoriop2.Utilidades;

/*
 
 */
public class MovimientoGatitos {

    static public int[] getCoordenadas(String coordenadaX, int coordenadaY) {
        int x = 0;
        if (coordenadaX.charAt(0) == 'A') {
            x = 0;
        }
        if (coordenadaX.charAt(0) == 'B') {
            x = 1;
        }
        if (coordenadaX.charAt(0) == 'C') {
            x = 2;
        }
        if (coordenadaX.charAt(0) == 'D') {
            x = 3;
        }
        if (coordenadaX.charAt(0) == 'E') {
            x = 4;
        }
        if (coordenadaX.charAt(0) == 'F') {
            x = 5;
        }
        int[] coordenadas = {x, coordenadaY};
        return coordenadas;
    }

    static public void colocarGatito(int x, int y, String[][] matriz, String color) {
        matriz[x][y] = "g" + color;
    }

    static public boolean yaHayGatito(int x, int y, String[][] matriz) {
        boolean hayGatito = false;
        if (matriz[x][y].toLowerCase().charAt(0) == 'g') {
            hayGatito = true;
        }
        return hayGatito;
    }

    static public void agregaGatitoACaja(String color, int[] caja) {
        if (color.contains("rojo")) {
            caja[0] = caja[0] + 1;
        }
        if (color.contains("azul")) {
            caja[0] = caja[0] + 1;
        }
    }

    static public void agregaGatoACaja(String color, int[] caja) {
        if (color.contains("rojo")) {
            caja[1] = caja[1] + 1;
        }
        if (color.contains("azul")) {
            caja[1] = caja[1] + 1;
        }
    }

    static public String seMueve(int xCoordenada, int yCoordenada, String[][] matriz) {
        String resp = "";

        if (xCoordenada == 0) {
            // PRIMER FILA
            if (yCoordenada == 0) {
                //PRIMER FILA PRIMER COLUMNA
                System.out.println("Cayo en 0 0");
                for (int i = xCoordenada; i <= (xCoordenada + 1); i++) {
                    for (int j = yCoordenada; j <= yCoordenada + 1; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            }
            if (yCoordenada == matriz[0].length - 1) {
                //PRIMER FILA ULTIMA COLUMNA
                System.out.println("Cayo en 0 5");
                for (int i = xCoordenada; i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= yCoordenada; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            }
            if (yCoordenada != 0 && yCoordenada != matriz[0].length - 1) {
                //PRIMER FILA CUALQUIER COLUMNA
                System.out.println("Cayo en 0 y cualquier otro");
                for (int i = xCoordenada; i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            }
        } else if (xCoordenada == 5) {
            //ULTIMA FILA
            if (yCoordenada == 0) {
                //ULTIMA FILA PRIMER COLUMNA
                System.out.println("Cae en el max X y en y 0");
                for (int i = (xCoordenada - 1); i <= xCoordenada; i++) {
                    for (int j = yCoordenada; j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            } else if (yCoordenada == matriz[0].length - 1) {
                //ULTIMA FILA ULTIMA COLUMNA
                System.out.println("Cae en el max X y en y max y");
                for (int i = (xCoordenada - 1); i <= xCoordenada; i++) {
                    for (int j = (yCoordenada - 1); j <= yCoordenada; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            } else {
                //ULTIMA FILA CUALQUIER COLUMNA
                for (int i = (xCoordenada - 1); i <= xCoordenada; i++) {
                    for (int j = (yCoordenada - 1); j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            }
        } else {
            //CUALQUIER FILA
            if (yCoordenada == 0) {
                //cualquier fila contra la iz
                for (int i = (xCoordenada - 1); i <= (xCoordenada + 1); i++) {
                    for (int j = yCoordenada; j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }
            } else if (yCoordenada == matriz[0].length - 1) {
                //cualquier fila contra la derecha
                for (int i = (xCoordenada - 1); i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= yCoordenada; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }

            } else {
                //cualquier otra
                for (int i = (xCoordenada - 1); i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                resp += i + "-" + j + "/";
                            }
                        }
                    }
                }

            }
        }
        return resp;
    }

    static public int[] saltoGato(String coordenadasGatosQueSeMueven, int xCoordenada, int yCoordenada, String[][] matriz, String color) {
        int diferenciaFila = 0;
        int diferenciaColumna = 0;
        int[] sumaGatosEnCaja = {0, 0};

        for (int i = 0; i <= (coordenadasGatosQueSeMueven.length() - 4); i = i + 4) {

            if (Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) > xCoordenada) {
                diferenciaFila = 1;
            } else if (Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) < xCoordenada) {
                diferenciaFila = -1;
            } else {
                diferenciaFila = 0;
            }

            if (Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) > yCoordenada) {
                diferenciaColumna = 1;
            } else if (Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) < yCoordenada) {
                diferenciaColumna = -1;
            } else {
                diferenciaColumna = 0;
            }

            Boolean filaNo = (Character.getNumericValue((coordenadasGatosQueSeMueven.charAt(i))) + diferenciaFila) < 0 || (Character.getNumericValue((coordenadasGatosQueSeMueven.charAt(i))) + diferenciaFila) >= matriz.length;
            Boolean columnaNo = (Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna) < 0 || (Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna) >= matriz[0].length;

            if (!filaNo && !columnaNo && matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) + diferenciaFila][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna].charAt(0) !=  'g') {
                matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) + diferenciaFila][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna] = "g" + color;
                matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))] = "0";
            } else {
                if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].contains("rojo")) {
                    sumaGatosEnCaja[0]= sumaGatosEnCaja[0] + 1;
                    System.out.println("mas un gato rojo");
                }

                if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].contains("azul")) {
                    sumaGatosEnCaja[1]++;
                    System.out.println("mas un gato azul");
                }
                matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))] = "0";
            }
        }
        System.out.println(sumaGatosEnCaja[0] + " " + sumaGatosEnCaja[1]);
        return sumaGatosEnCaja;
    }
}

/*
falta determinar como agragamos los gatos que caen afuera a las respectivas cajas y checkear los colores y cambios de colores de las fichas en la mesa
definir forma de ganar
login
eleccion de jugador en la lista de jugadores
registro de jugadores
*/
