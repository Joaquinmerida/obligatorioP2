/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

    static public void seMueve(int xNuevoGatito, int yNuevoGatito, String[][] matriz) {
        int counter = 0;
        String[][] mat = new String[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                mat[i][j] = "ok";
            }
        }

        if (matriz.length > xNuevoGatito + 1) {
            if (matriz[0].length > yNuevoGatito + 1) {
                //esta en el medio
                if (yNuevoGatito == 0) {
                    //esta a la izquierda
                    if (xNuevoGatito == 0) {
                        //esta arriba a la iz
                    }
                }
                if (xNuevoGatito == 0) {
                }

            } else {
                //esta contra la derecha
            }

        } else {
            if (matriz[0].length >= yNuevoGatito + 1) {
                //esta abajo en el medio
            } else {
                //esta abajo contra la derecha
            }

        }

        for (int i = xNuevoGatito - 1; i <= xNuevoGatito + 1; i++) {
            for (int j = yNuevoGatito - 1; j <= yNuevoGatito + 1; j++) {
                if (matriz[i][j].charAt(0) == 'g' && (i != 1 && j != 1)) {
                    mat[i][j] = "A" + i + " " + j;
                    counter++;
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.println(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(counter);
    }

    /*
    
    static public void boolean caeAfuera(){
    }
    
    static public  void boolean gatoMovido(){
    }
     */
}
