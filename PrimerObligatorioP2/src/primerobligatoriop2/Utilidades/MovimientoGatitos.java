/*

 */
package primerobligatoriop2.Utilidades;

/*
 
 */
public class MovimientoGatitos {

    static public int[] getCoordenadas(String coordenadaX, int coordenadaY) {
        int x = 0;
        if (coordenadaX.charAt(0) == 'A' || coordenadaX.charAt(0) == 'a') {
            x = 0;
        } else if (coordenadaX.charAt(0) == 'B' || coordenadaX.charAt(0) == 'b') {
            x = 1;
        } else if (coordenadaX.charAt(0) == 'C' || coordenadaX.charAt(0) == 'c') {
            x = 2;
        } else if (coordenadaX.charAt(0) == 'D' || coordenadaX.charAt(0) == 'd') {
            x = 3;
        } else if (coordenadaX.charAt(0) == 'E' || coordenadaX.charAt(0) == 'e') {
            x = 4;
        } else if (coordenadaX.charAt(0) == 'F' || coordenadaX.charAt(0) == 'f') {
            x = 5;
        }
        int[] coordenadas = {x, coordenadaY};
        return coordenadas;
    }

    static public void colocarGatito(int x, int y, String[][] matriz, String color) {
        matriz[x][y] = "g" + color;
    }

    static public void colocarGato(int x, int y, String[][] matriz, String color) {
        matriz[x][y] = "G" + color;
    }

    static public boolean yaHayGatito(int x, int y, String[][] matriz) {
        boolean hayGatito = false;
        if (matriz[x][y].charAt(0) == 'g') {
            hayGatito = true;
        }
        return hayGatito;
    }

    static public boolean yaHayGato(int x, int y, String[][] matriz) {
        boolean hayGato = false;
        if (matriz[x][y].charAt(0) == 'G') {
            hayGato = true;
        }
        return hayGato;
    }


    static public void agregaGatitoACaja(String color, int[] caja) {
        if (color.contains("rojo")) {
            caja[0] = caja[0] + 1;
        }
        if (color.contains("azul")) {
            caja[2] = caja[2] + 1;
        }
    }

    static public void agregaGatoACaja(String color, int[] caja, int suma) {
        if (color.contains("rojo")) {
            caja[1] = caja[1] + suma;
        }
        if (color.contains("azul")) {
            caja[3] = caja[3] + suma;
        }
    }

    static public void desaparecerGatitos(String[][] matriz, String[] coordenadas) {
        for (int i = 0; i < coordenadas.length; i++) {
            matriz[(Character.getNumericValue(coordenadas[i].charAt(0)))][(Character.getNumericValue(coordenadas[i].charAt(1)))] = "0";
            System.out.println((Character.getNumericValue(coordenadas[i].charAt(0))) + " " + Character.getNumericValue(coordenadas[i].charAt(1)));
        }
    }

    static public void sacaConCoordenadas(String[][] mat, int coordenadaX, int coordenadaY) {
        mat[coordenadaX][coordenadaY] = "0";
    }

    static public String seMueve(int xCoordenada, int yCoordenada, String[][] matriz, String gatoColocado) {
        String resp = "";
        Boolean gatito = false;
        if (xCoordenada == 0) {
            // PRIMER FILA
            if (yCoordenada == 0) {
                //PRIMER FILA PRIMER COLUMNA
                for (int i = xCoordenada; i <= (xCoordenada + 1); i++) {
                    for (int j = yCoordenada; j <= yCoordenada + 1; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }
            }
            //               g             G
            if (yCoordenada == matriz[0].length - 1) {
                //PRIMER FILA ULTIMA COLUMNA
                for (int i = xCoordenada; i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= yCoordenada; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }
            }
            if (yCoordenada != 0 && yCoordenada != matriz[0].length - 1) {
                //PRIMER FILA CUALQUIER COLUMNA
                for (int i = xCoordenada; i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }
            }
        } else if (xCoordenada == 5) {
            //ULTIMA FILA
            if (yCoordenada == 0) {
                //ULTIMA FILA PRIMER COLUMNA
                for (int i = (xCoordenada - 1); i <= xCoordenada; i++) {
                    for (int j = yCoordenada; j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }
            } else if (yCoordenada == matriz[0].length - 1) {
                //ULTIMA FILA ULTIMA COLUMNA
                for (int i = (xCoordenada - 1); i <= xCoordenada; i++) {
                    for (int j = (yCoordenada - 1); j <= yCoordenada; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }
            } else {
                //ULTIMA FILA CUALQUIER COLUMNA
                for (int i = (xCoordenada - 1); i <= xCoordenada; i++) {
                    for (int j = (yCoordenada - 1); j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
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
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }
            } else if (yCoordenada == matriz[0].length - 1) {
                //cualquier fila contra la derecha
                for (int i = (xCoordenada - 1); i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= yCoordenada; j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }

            } else {
                //cualquier otra
                for (int i = (xCoordenada - 1); i <= (xCoordenada + 1); i++) {
                    for (int j = (yCoordenada - 1); j <= (yCoordenada + 1); j++) {
                        if (!(i == xCoordenada && j == yCoordenada)) {
                            if (gatoColocado.contains("G")) {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0) || matriz[i][j].charAt(0) == 'g') {
                                    resp += i + "-" + j + "/";
                                }
                            } else {
                                if (matriz[i][j].charAt(0) == matriz[xCoordenada][yCoordenada].charAt(0)) {
                                    resp += i + "-" + j + "/";
                                }
                            }
                        }
                    }
                }

            }
        }
        return resp;
    }

    static public void saltoGato(String coordenadasGatosQueSeMueven, int xCoordenada, int yCoordenada, String[][] matriz, int[] cajaGatitos) {
        int diferenciaFila = 0;
        int diferenciaColumna = 0;
        String color = "";

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

            if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].contains("rojo")) {
                color = "rojo";
            }
            if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].contains("azul")) {
                color = "azul";
            }
            if (!filaNo && !columnaNo) {
                if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) + diferenciaFila][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna].charAt(0) != 'g' && matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) + diferenciaFila][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna].charAt(0) != 'G') {
                    matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i)) + diferenciaFila][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2)) + diferenciaColumna] = matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].charAt(0) + color;
                    matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))] = "0";
                }
            } else {
                if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].contains("rojo")) {
                    agregaGatitoACaja("rojo", cajaGatitos);
                    System.out.println("mas un gato rojo");
                }
                if (matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))].contains("azul")) {
                    agregaGatitoACaja("azul", cajaGatitos);
                    System.out.println("mas un gato azul");
                }
                matriz[Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i))][Character.getNumericValue(coordenadasGatosQueSeMueven.charAt(i + 2))] = "0";
            }
        }
    }
}

/*

login
registro de jugadores


 */
