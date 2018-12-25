/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.UniformedSearch;

import chess.Fringe.UCSFringe;
import chess.dataStructure.Node;
import chess.dataStructure.State;
import chess.dataStructure.StatesList;

/**
 *
 * @author Hoda_msw
 */
public class UCS {
    public UCS(State initial, State goal){
        StatesList statesList = new StatesList();
        statesList.reset();
        double time = System.nanoTime();
        System.out.println("\t Algorithm: UCS");
        int NodeCount = 0;
        Node start = new Node(0, null, initial, 0);
        NodeCount++;
        UCSFringe fringe = new UCSFringe();
        fringe.insert(start);
        int maxFringeSize = fringe.size;
        Node temp;
        while (true) {
            if (fringe.isEmpty()) {
                System.out.println("Failure");
                System.out.println("##############################");
                break;
            }
            temp = fringe.remove();
            if (temp.state.isEqual(goal)) {
                System.out.println("Number of movements: " + temp.depth + "\n");
                PrintOutput.printSteps(temp);
                System.out.println("\nNumber of generated nodes: " + NodeCount);
                System.out.println("Maximum nodes in RAM: " + maxFringeSize);
                System.out.println("Time: " + (System.nanoTime() - time) / 1000000000);
                System.out.println("##############################");
                break;
            } else {
                temp.Successore();
                for (int i = 0; i < temp.successor.size(); i++) {
                    NodeCount++;
                    if (statesList.find(temp.successor.get(i).state) == false) {
                        statesList.insert(temp.successor.get(i).state);
                        fringe.insert(temp.successor.get(i));
                    }
                }
                maxFringeSize = Integer.max(maxFringeSize, fringe.size);
            }
        }
    
    }
}
