package com.gabrielspassos.poc;

public class Main {

    public static void main(String[] args) {

        Node[][] matriz = new Node[7][7];
        buildMatriz(matriz);
        System.out.println("Matriz:  ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.println(matriz[i][j] +""+ j);
            }
        }

        Bfs bfs = new Bfs();
        bfs.search(matriz[0][3],matriz[6][0],matriz);
    }

    private static void buildMatriz(Node[][] matriz){
        matriz[0][0] = new Node(0,false);
        matriz[0][1] = new Node(0,true);
        matriz[0][2] = new Node(0,true);
        matriz[0][3] = new Node(0,true);
        matriz[0][4] = new Node(0,false);
        matriz[0][5] = new Node(0,false);
        matriz[0][6] = new Node(0,false);

        matriz[1][0] = new Node(1,true);
        matriz[1][1] = new Node(1,false);
        matriz[1][2] = new Node(1,false);
        matriz[1][3] = new Node(1,true);
        matriz[1][4] = new Node(1,false);
        matriz[1][5] = new Node(1,true);
        matriz[1][6] = new Node(1,false);

        matriz[2][0] = new Node(2,true);
        matriz[2][1] = new Node(2,false);
        matriz[2][2] = new Node(2,false);
        matriz[2][3] = new Node(2,true);
        matriz[2][4] = new Node(2,false);
        matriz[2][5] = new Node(2,false);
        matriz[2][6] = new Node(2,false);

        matriz[3][0] = new Node(3,true);
        matriz[3][1] = new Node(3,true);
        matriz[3][2] = new Node(3,true);
        matriz[3][3] = new Node(3,false);
        matriz[3][4] = new Node(3,false);
        matriz[3][5] = new Node(3,false);
        matriz[3][6] = new Node(3,true);

        matriz[4][0] = new Node(4,false);
        matriz[4][1] = new Node(4,false);
        matriz[4][2] = new Node(4,false);
        matriz[4][3] = new Node(4,false);
        matriz[4][4] = new Node(4,false);
        matriz[4][5] = new Node(4,true);
        matriz[4][6] = new Node(4,true);

        matriz[5][0] = new Node(5,false);
        matriz[5][1] = new Node(5,true);
        matriz[5][2] = new Node(5,false);
        matriz[5][3] = new Node(5,false);
        matriz[5][4] = new Node(5,true);
        matriz[5][5] = new Node(5,false);
        matriz[5][6] = new Node(5,true);

        matriz[6][0] = new Node(6,false);
        matriz[6][1] = new Node(6,false);
        matriz[6][2] = new Node(6,false);
        matriz[6][3] = new Node(6,true);
        matriz[6][4] = new Node(6,true);
        matriz[6][5] = new Node(6,true);
        matriz[6][6] = new Node(6,false);

    }
}