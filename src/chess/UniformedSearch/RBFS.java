/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.UniformedSearch;

import chess.Fringe.AstarFring;
import chess.dataStructure.Node;
import chess.dataStructure.State;
import chess.dataStructure.StatesList;

/**
 *
 * @author Hoda_msw
 */
public class RBFS {
    public RBFS(State initial,State goal){
        StatesList statesList = new StatesList();
        statesList.reset();
        double time = System.nanoTime();
        System.out.println("\t Algorithm: RBFS");
        int NodeCount = 0;
        Node start = new Node(0, null, initial, 0);
        NodeCount++;
        AstarFring fringe = new AstarFring();
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
            int nextFn = fringe.show();
            if (temp.state.isEqual(goal)) {
                System.out.println("Action: " + temp.depth + "\n");
                PrintOutput.printSteps(temp);
                System.out.println("\nNumber of generated nodes: " + NodeCount);
                System.out.println("Maximum nodes in RAM: " + maxFringeSize);
                System.out.println("Time: " + (System.nanoTime() - time) / 1000000000);
                System.out.println("##############################");
                break;
            } else {
                temp.Successore();
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < temp.successor.size(); i++) {
                    if (temp.successor.get(i).Fn < min) {
                        min = temp.successor.get(i).Fn;
                    }
                }
                if (min <= nextFn) {
                    for (int i = 0; i < temp.successor.size(); i++) {
                        NodeCount++;
                        if (statesList.find(temp.successor.get(i).state) == false) {
                            statesList.insert(temp.successor.get(i).state);
                            fringe.insert(temp.successor.get(i));
                        }
                    }
                    maxFringeSize = Integer.max(maxFringeSize, fringe.size);
                } else {
                    temp.Fn = min;
                    fringe.insert(temp);
                }
            }
        }
    }
}
