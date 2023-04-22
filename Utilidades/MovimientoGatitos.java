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

    static public void colocarGatito(int x,int  y,String[][] matriz, String color) {
        matriz[x][y] = "g" + color;
    }

    static public boolean yaHayGatito() {
        boolean hayGatito = false;
        return hayGatito;
    }
}
