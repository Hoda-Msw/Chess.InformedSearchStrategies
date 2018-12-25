/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.UniformedSearch;

import chess.Fringe.DFSFringe;
import chess.dataStructure.Node;
import chess.dataStructure.State;
import chess.dataStructure.StatesList;

/**
 *
 * @author Hoda_msw
 */
public class IDS {
    public IDS (State initial, State goal){
        double time = System.nanoTime();
        System.out.println("\t Algorithm: IDS");
        int NodeCount;
        Node start = new Node(0, null, initial, 0);
        StatesList statesList = new StatesList();
        boolean finished = false;
        for (int j = 0; !finished && j < 40; j++) {
            NodeCount = 0;
            statesList.reset();
            DFSFringe fringe = new DFSFringe();
            fringe.insert(start);
            NodeCount++;
            Node temp;
            int maxFringeSize = fringe.size;
            
            while (true) {
                if (fringe.isEmpty()) {
                    break;
                }
                temp = fringe.remove();
                if (temp.state.isEqual(goal)) {
                    System.out.println("Number of movements:  " + temp.depth + "\n");
                    PrintOutput.printSteps(temp);
                    System.out.println("\nNumber of generated nodes: " + NodeCount);
                    System.out.println("Maximum nodes in RAM: " + maxFringeSize);
                    System.out.println("Time: " + (System.nanoTime() - time) / 1000000000);
                    System.out.println("##############################");
                    finished = true;
                    break;
                } else if (temp.depth < j) {
                    temp.Successore();
                    for (int i = 0; i < temp.successor.size(); i++) {
                        NodeCount++;
                        if (statesList.find(temp.successor.get(i).state) == false) {
                            statesList.insert(temp.successor.get(i).state);
                            fringe.insert(temp.successor.get(i));
                        }
                    }
                }
                maxFringeSize = Integer.max(maxFringeSize, fringe.size);
            }
        }
        if (!finished) {
            System.out.println("Failure");
            System.out.println("##############################");
        }
        
}
}