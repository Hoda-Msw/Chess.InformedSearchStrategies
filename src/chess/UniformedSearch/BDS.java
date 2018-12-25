/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.UniformedSearch;

import chess.Fringe.BFSFringe;
import chess.dataStructure.Node;
import chess.dataStructure.State;
import chess.dataStructure.StatesList;

/**
 *
 * @author Hoda_msw
 */
public class BDS {
    public BDS(State initial,State goal){
        StatesList statesList1 = new StatesList();
        StatesList statesList2 = new StatesList();
        statesList1.reset();
        statesList2.reset();
        double time = System.nanoTime();
        System.out.println("\t Algorithm: BDS");
        int NodeCount = 0;
        // add start node
        Node start = new Node(0, null, initial, 0);
        NodeCount++;
        BFSFringe fringe1 = new BFSFringe();
        fringe1.insert(start);
        //add end node
        Node end = new Node(0, null, goal, 0);
        NodeCount++;
        BFSFringe fringe2 = new BFSFringe();
        fringe2.insert(end);
        int maxFringeSize = fringe1.size + fringe2.size;
        Node temp1;
        Node temp2;
        boolean finished = false;
        while (!finished) {
            if (fringe1.isEmpty() || fringe2.isEmpty()) {
                System.out.println("Failure");
                System.out.println("##############################");
                break;
            }
            for (int i = 0; !finished && i < fringe1.size; i++) {
                for (int j = 0; j < fringe2.size; j++) {
                    if (fringe1.get(i).state.isEqual(fringe2.get(j).state)) {
                        temp1 = fringe1.get(i);
                        temp2 = fringe2.get(j);
                        System.out.println("Number of movements: " + (temp1.depth + Math.abs(end.depth - temp2.depth)) + "\n");
//                        printStepsBDS(temp1, temp2);
                        System.out.println("\nNumber of generated nodes: " + NodeCount);
                        System.out.println("Maximum nodes in RAM: " + maxFringeSize);
                        System.out.println("Time: " + (System.nanoTime() - time) / 1000000000);
                        System.out.println("##############################");
                        finished = true;
                        break;
                    }
                }
            }
            Node[] array1 = new Node[fringe1.size];
            for (int i = 0; i < array1.length; i++) {
                array1[i] = fringe1.remove();
            }
            for (Node array11 : array1) {
                array11.Successore();
                for (int i = 0; !finished && i < array11.successor.size(); i++) {
                    NodeCount++;
                    if (statesList1.find(array11.successor.get(i).state) == false) {
                        statesList1.insert(array11.successor.get(i).state);
                        fringe1.insert(array11.successor.get(i));
                    }
                }
            }
            maxFringeSize = Integer.max(maxFringeSize, fringe1.size + fringe2.size);
            for (int i = 0; !finished && i < fringe1.size; i++) {
                for (int j = 0; j < fringe2.size; j++) {
                    if (fringe1.get(i).state.isEqual(fringe2.get(j).state)) {
                        temp1 = fringe1.get(i);
                        temp2 = fringe2.get(j);
                        System.out.println("Action: " + (temp1.depth + Math.abs(end.depth - temp2.depth)) + "\n");
//                        printStepsBDS(temp1, temp2);
                        System.out.println("\nNumber of generated nodes: " + NodeCount);
                        System.out.println("Maximum nodes in RAM: " + maxFringeSize);
                        System.out.println("Time: " + (System.nanoTime() - time) / 1000000000);
                        finished = true;
                        break;
                    }
                }
            }
            Node[] array2 = new Node[fringe2.size];
            for (int i = 0; i < array2.length; i++) {
                array2[i] = fringe2.remove();
            }
            for (Node array21 : array2) {
                array21.Successore();
                for (int i = 0; !finished && i < array21.successor.size(); i++) {
                    NodeCount++;
                    if (statesList2.find(array21.successor.get(i).state) == false) {
                        statesList2.insert(array21.successor.get(i).state);
                        fringe2.insert(array21.successor.get(i));
                    }
                }
            }
            maxFringeSize = Integer.max(maxFringeSize, fringe1.size + fringe2.size);
        }
    }
}
