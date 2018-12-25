/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.dataStructure;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author Hoda_msw
 */
public class CreateStates {

    public State initial;
    public static State goal;

    public CreateStates() {
        try {
            FileInputStream file = new FileInputStream(new File("in.txt"));
            Scanner input = new Scanner(file);
            int n = input.nextInt();
            int m = input.nextInt();

            //reading start state
            initial = new State(n, m);
            for (int i = 0; i < n; i++) {
                String str = input.next();
                for (int j = 0; j < m; j++) {
                    switch (str.charAt(j)) {
                        case 'k':
                            initial.board[i][j] = 2;
                            break;
                        case 'p':
                            initial.board[i][j] = 1;
                            break;
                        case '.':
                            initial.board[i][j] = 0;
                            break;
                        default:
                            initial.board[i][j] = 0;
                            break;
                    }
                }
            }

            //reading goal state
            goal = new State(n, m);
            for (int i = 0; i < n; i++) {
                String str = input.next();
                for (int j = 0; j < n; j++) {
                    switch (str.charAt(j)) {
                        case 'k':
                            goal.board[i][j] = 2;
                            break;
                        case 'p':
                            goal.board[i][j] = 1;
                            break;
                        case '.':
                            goal.board[i][j] = 0;
                            break;
                        default:
                            goal.board[i][j] = 0;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("file didn't found !");
            System.out.println(e.getMessage());
        }
    }

    public State getInitial() {
        return initial;
    }

    public static State getGoal() {
        return goal;
    }

}
