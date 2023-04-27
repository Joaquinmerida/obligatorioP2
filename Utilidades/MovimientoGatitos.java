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

    static public void saltoGato(String coordenadasGatosQueSeMueven, int xCoordenada, int yCoordenada, String[][] matriz, String color) {

        
        for (int i = 0; i < coordenadasGatosQueSeMueven.length(); i = i + 4) {
            //comparar las filas y las columnas
            // cambiar las coordenadas y borrar donde estaba el gato
        }
    }
}


/*
    
    static public void boolean caeAfuera(){
    }
    
    static public  void boolean gatoMovido(){
    }
 */
