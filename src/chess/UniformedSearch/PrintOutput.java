/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.UniformedSearch;

import chess.dataStructure.Node;
import chess.dataStructure.State;

/**
 *
 * @author Hoda_msw
 */
public class PrintOutput {
    public PrintOutput(Node goal){
        printSteps(goal);
    }
    
    public static void printSteps(Node goal){
        State[] steps = new State[goal.depth + 1];
        Node q = goal;
        for (int i = goal.depth; i >= 0; i--) {
            steps[i] = q.state;
            q = q.parent;
        }
        for (int i = 0; i < goal.depth; i++) {
            printMovments(steps[i], steps[i + 1]);
        }
    }
    
    public static void printMovments(State initial, State goal) {
        boolean swich = true;
        for (int i = 0; swich && i < initial.board.length; i++) {
            for (int j = 0; j < initial.board[i].length; j++) {
                if (goal.board[i][j] - initial.board[i][j] == 1) {
                    if (j < initial.board[i].length - 1 && goal.board[i][j + 1] - initial.board[i][j + 1] == -1) {
                        System.out.println("Move left: from (" + i + ", " + (j + 1) + ") to (" + i + ", " + j + ")");      //move  left
                    } else if (i < initial.board.length - 1 && goal.board[i + 1][j] - initial.board[i + 1][j] == -1) {
                        System.out.println("Move up: from (" + (i + 1) + ", " + j + ") to (" + i + ", " + j + ")");      //move  up
                    }
                    swich = false;
                    break;
                } else if (goal.board[i][j] - initial.board[i][j] == -1) {
                    if (j < initial.board[i].length - 1 && goal.board[i][j + 1] - initial.board[i][j + 1] == 1) {
                        System.out.println("Move right: from (" + i + ", " + j + ") to (" + i + ", " + (j + 1) + ")");      //move right
                    } else if (i < initial.board.length - 1 && goal.board[i + 1][j] - initial.board[i + 1][j] == 1) {
                        System.out.println("Move down: from (" + i + ", " + j + ") to (" + (i + 1) + ", " + j + ")");      //move down
                    }
                    swich = false;
                    break;
                }
            }
        }
    }
    
}
