/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.dataStructure;

/**
 *
 * @author Hoda_msw
 */
public class State {

    public int[][] board;

    public State(int n, int m) {
        board = new int[n][m];
    }

    public boolean isEqual(State s) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (this.board[i][j] != s.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void set(State s) {
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(s.board[i], 0, board[i], 0, board[i].length);
        }
    }

    public int HeuristicFunction() {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != CreateStates.getGoal().board[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
