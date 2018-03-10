package com.gabrielspassos.poc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    public boolean search(Node root, Node destiny, Node[][] matriz){
        //Desmarcar todos os vértices
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j].setChecked(false);
            }
        }

        Queue queue = new LinkedList();
        //Definir fila Q vazia
        queue.clear();
        //Marcar raiz e inserir raiz na fila Q
        root.setChecked(true);
        queue.add(root);

        System.out.print("Start: ");
        //Enquanto Q não estiver vazia
        while (!queue.isEmpty()){
            //Retirar v de Q
            Node node = (Node)queue.remove();
            System.out.print(node.getValor() + " - ");
            if(node.getValor().equals(destiny.getValor())){
                return true;
            }
            //Para todo vizinho w de v faça
            for (Node neighbor: getNeighbors(matriz,node)) {
                //Se w não estiver marcado
                if(!neighbor.getChecked()){
                    //marcar w
                    matriz[neighbor.getValor()][node.getValor()].setChecked(true);
                    //inserir w em Q
                    queue.add(matriz[neighbor.getValor()][node.getValor()]);
                }

            }
        }

        return false;

    }

    private List<Node> getNeighbors(Node[][]matriz, Node nodeRoot){
        List<Node> neighborsNodes = new ArrayList<>();

        for (int i = 0; i < matriz.length; i++) {
            if(matriz[nodeRoot.getValor()][i].getConnection()){
                neighborsNodes.add(matriz[i][nodeRoot.getValor()]);
            }
        }
        return neighborsNodes;
    }

}
