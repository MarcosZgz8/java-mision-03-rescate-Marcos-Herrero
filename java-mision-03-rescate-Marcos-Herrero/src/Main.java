import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] matriz = new char[10][10];
        int vidas = 10;

        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz.length; columna++) {
                matriz[fila][columna] = '*';
            }
        }

        char YODA = 'R';
        matriz[0][0] = YODA;

        char CASILLA_FINAL = 'F';
        matriz[9][9] = CASILLA_FINAL;

        Random aleatorios = new Random();

        int filaAleatoria2 = aleatorios.nextInt(10);
        int columnaAleatoria2 = aleatorios.nextInt(10);

        do {
            filaAleatoria2 = aleatorios.nextInt(10);
            columnaAleatoria2 = aleatorios.nextInt(10);
        } while (matriz[filaAleatoria2][columnaAleatoria2] != '*');

        matriz[filaAleatoria2][columnaAleatoria2] = 'B';

        for (int i = 0; i < 5; i++) {
            do {
                filaAleatoria2 = aleatorios.nextInt(10);
                columnaAleatoria2 = aleatorios.nextInt(10);
            } while (matriz[filaAleatoria2][columnaAleatoria2] != '*');

            matriz[filaAleatoria2][columnaAleatoria2] = 'X';
        }

        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                System.out.print(matriz[fila][columna] + "  ");
            }
            System.out.println();
        }

        int filaYoda = 0;
        int columnaYoda = 0;
        boolean rescateCompletado = false;
        Scanner sc = new Scanner(System.in);

        while (!rescateCompletado) {
            System.out.println("Te puedes desplazar con las letras W A S D");
            System.out.print("Dime una letra: ");
            char desplazamiento = sc.next().charAt(0);

            int nuevaFila = filaYoda;
            int nuevaColumna = columnaYoda;

            if (desplazamiento == 'W') {
                nuevaFila--;
            } else if (desplazamiento == 'S') {
                nuevaFila++;
            } else if (desplazamiento == 'A') {
                nuevaColumna--;
            } else if (desplazamiento == 'D') {
                nuevaColumna++;
            } else {
                System.out.println("Letra no válida.");
            }

            if (desplazamiento == 'W' || desplazamiento == 'A'
                    || desplazamiento == 'S' || desplazamiento == 'D') {
                if (nuevaFila < 0 || nuevaFila >= matriz.length
                        || nuevaColumna < 0 || nuevaColumna >= matriz[0].length) {
                    System.out.println("No te puedes salir.");
                } else {
                    char casillaDestino = matriz[nuevaFila][nuevaColumna];

                    if (casillaDestino == 'X') {
                        System.out.println("Movimiento bloqueado.");
                    } else {
                        if (casillaDestino == 'B') {
                            vidas += 2;
                            System.out.println("Has recogido una batería. Sumas 2 vidas.");
                        } else if (casillaDestino == 'F') {
                            System.out.println("Rescate completado.");
                            rescateCompletado = true;
                        }

                        matriz[filaYoda][columnaYoda] = '*';
                        filaYoda = nuevaFila;
                        columnaYoda = nuevaColumna;
                        matriz[filaYoda][columnaYoda] = YODA;
                    }
                }
            }

            for (int fila = 0; fila < matriz.length; fila++) {
                for (int columna = 0; columna < matriz[fila].length; columna++) {
                    System.out.print(matriz[fila][columna] + "  ");
                }
                System.out.println();
            }

            System.out.println("Vidas: " + vidas);
        }
    }
}