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
public class IDAStar {
    @SuppressWarnings("empty-statement")
    public IDAStar(State initial , State goal) {
        double time = System.nanoTime();
        System.out.println("\t Algorithm: IDA*");
        int NodeCount;
        Node start = new Node(0, null, initial, 0);
        StatesList statesList = new StatesList();
        boolean finished = false;
        int cutOff = start.Fn;
        
        while (!finished) {
            int nextCutOff = Integer.MAX_VALUE;
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
                    System.out.println("Action: " + temp.depth + "\n");
                    PrintOutput.printSteps(temp);
                    System.out.println("\nNumber of generated nodes: " + NodeCount);
                    System.out.println("Maximum nodes in RAM: " + maxFringeSize);
                    System.out.println("Time: " + (System.nanoTime() - time) / 1000000000);
                    System.out.println("##############################");
                    finished = true;
                    break;
                } else {
                    temp.Successore();
                    if (temp.successor.isEmpty()) {
                        Node next = fringe.remove();
                        fringe.insert(next);
                        while (!(next.parent.state.isEqual(statesList.remove())));
                        statesList.insert(next.parent.state);
                    }
                    for (int i = 0; i < temp.successor.size() ; i++) {
                        NodeCount++;
                        if (statesList.find(temp.successor.get(i).state) == false && temp.successor.get(i).Fn <= cutOff) {
                            statesList.insert(temp.successor.get(i).state);
                            fringe.insert(temp.successor.get(i));
                        } else if (temp.successor.get(i).Fn >= cutOff) {
                            if (temp.successor.get(i).Fn < nextCutOff) {
                                nextCutOff = temp.successor.get(i).Fn;
                            }
                        }
                    }
                }
                maxFringeSize = Integer.max(maxFringeSize, fringe.size);
            }
            cutOff = nextCutOff;
        }
        if (!finished) {
            System.out.println("Failure");
            System.out.println("##############################");
        }
    }
}
