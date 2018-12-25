/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.dataStructure;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Hoda_msw
 */
public class Node {

    public final int KING = 2;
    public final int SOLDURE = 1;
    public final int EMPTY = 0;
    public State state;
    public int depth;
//    public int cost;
    public int Gn;
    public int Fn;
    public int Hn;
    public Node parent;
    public List<Node> successor = new LinkedList<>();

    //constractor
    public Node(int depth, Node parent, State state, int Gn) {
        this.depth = depth;
        this.parent = parent;
        this.state = new State(state.board.length, state.board[0].length);
        this.state.set(state);
        this.Gn = Gn;
        this.Hn = this.state.HeuristicFunction();
        Fn = Hn + Gn;
    }

    //checks if around it is empty and able to move on
    public boolean Checkaround(int row, int col) {

        //States which are in the corners , so they have to check 3 sides
        if (row == 0 && col == 0) {         //top left corner     
            if (state.board[row][col + 1] == 0 && state.board[row + 1][col + 1] == 0 && state.board[row + 1][col] == 0) {
                return true;
            }
        } else if (row == 0 && col == state.board[row].length - 1) {        //top right corner
            if (state.board[row][col - 1] == 0 && state.board[row + 1][col - 1] == 0 && state.board[row + 1][col] == 0) {
                return true;
            }
        } else if (row == state.board.length - 1 && col == 0) {         //down left corner
            if (state.board[row - 1][col] == 0 && state.board[row - 1][col + 1] == 0 && state.board[row][col + 1] == 0) {
                return true;
            }
        } else if (row == state.board.length - 1 && col == state.board[row].length - 1) {       //down right corner
            if (state.board[row][col - 1] == 0 && state.board[row - 1][col - 1] == 0 && state.board[row - 1][col] == 0) {
                return true;
            }
        } //States which are on the eadges of the board , so they have to check 5 sides
        else if (row == 0) {        //top eadge
            if (state.board[row][col - 1] == 0 && state.board[row + 1][col - 1] == 0 && state.board[row + 1][col] == 0 && state.board[row + 1][col + 1] == 0 && state.board[row][col + 1] == 0) {
                return true;
            }
        } else if (col == 0) {     //left eadge      
            if (state.board[row - 1][col] == 0 && state.board[row - 1][col + 1] == 0 && state.board[row][col + 1] == 0 && state.board[row + 1][col + 1] == 0 && state.board[row + 1][col] == 0) {
                return true;
            }
        } else if (row == state.board.length - 1) {     //down eadge
            if (state.board[row][col - 1] == 0 && state.board[row - 1][col - 1] == 0 && state.board[row - 1][col] == 0 && state.board[row - 1][col + 1] == 0 && state.board[row][col + 1] == 0) {
                return true;
            }
        } else if (col == state.board[row].length - 1) {        //right eadge
            if (state.board[row - 1][col] == 0 && state.board[row - 1][col - 1] == 0 && state.board[row][col - 1] == 0 && state.board[row + 1][col - 1] == 0 && state.board[row + 1][col] == 0) {
                return true;
            }
        } //States which are in the middle of the board ans have to check 8 sides of the current 
        else if (state.board[row - 1][col - 1] == 0 && state.board[row - 1][col] == 0 && state.board[row - 1][col + 1] == 0 && state.board[row][col + 1] == 0 && state.board[row][col - 1] == 0 && state.board[row + 1][col + 1] == 0 && state.board[row + 1][col] == 0 && state.board[row + 1][col - 1] == 0) {
            return true;
        }
        return false;
    }

    public void Successore() {

        for (int i = 0; i < state.board.length; i++) {
            for (int j = 0; j < state.board[i].length; j++) {

                //(moving a soldure)
                if (state.board[i][j] == SOLDURE) {       //if soldure
                    if (i > 0) {      //because i=0 is the goal for a soldure
                        state.board[i][j] = 0;        //remove current node
                        if (state.board[i - 1][j] == 0) {       //one step up
                            Node n = new Node(depth + 1, this, state, Gn + 1);
                            n.state.board[i][j] = 0;
                            n.state.board[i - 1][j] = 1;
                            successor.add(n);
                        }
                        state.board[i][j] = 1;      //add current node again                         
                    }
                } else if (state.board[i][j] == KING) {     //moving a king
                    if (state.board[i][j] == CreateStates.getGoal().board[i][j]) {//    System.out.println("king moved");
                        break;
                    }
                    if (i > 0) {        //move up
                        state.board[i][j] = 0;
                        if (state.board[i - 1][j] == 0 && Checkaround(i, j)) {
                            System.out.println("test1");
                            Node n = new Node(depth + 1, this, state, Gn + 2);
                            n.state.board[i][j] = 0;
                            n.state.board[i - 1][j] = 2;
                            successor.add(n);
                        }
                        state.board[i][j] = 2;
                    }
                    if (i < state.board.length - 1) {       //move down
                        state.board[i][j] = 0;
                        if (state.board[i + 1][j] == 0 && Checkaround(i, j)) {
                            System.out.println("test2");
                            Node n = new Node(depth + 1, this, state, Gn + 2);
                            n.state.board[i][j] = 0;
                            n.state.board[i + 1][j] = 2;
                            successor.add(n);
                        }
                        state.board[i][j] = 2;
                    }
                    if (j > 0) {        //move right
                        state.board[i][j] = 0;
                        if (state.board[i][j - 1] == 0 && Checkaround(i, j)) {
                            System.out.println("test3");
                            Node n = new Node(depth + 1, this, state, Gn + 2);
                            n.state.board[i][j] = 0;
                            n.state.board[i][j - 1] = 2;
                            successor.add(n);
                        }
                        state.board[i][j] = 2;
                    }
                    if (j < state.board.length - 1) {       //move left
                        state.board[i][j] = 0;
                        if (state.board[i][j + 1] == 0 && Checkaround(i, j)) {
                            System.out.println("test4");
                            Node n = new Node(depth + 1, this, state, Gn + 2);
                            n.state.board[i][j] = 0;
                            n.state.board[i][j + 1] = 2;
                            successor.add(n);
                        }
                        state.board[i][j] = 2;
                    }
                }
            }
        }
    }
}
