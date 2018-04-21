/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.main;

import chess.UniformedSearch.AStar;
import chess.UniformedSearch.BDS;
import chess.UniformedSearch.BFS;
import chess.UniformedSearch.DFS;
import chess.UniformedSearch.GBFS;
import chess.UniformedSearch.IDAStar;
import chess.UniformedSearch.IDS;
import chess.UniformedSearch.RBFS;
import chess.UniformedSearch.UCS;
import chess.dataStructure.CreateStates;

/**
 *
 * @author Hoda_msw
 */
public class ChessUninformedSearchStrategies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CreateStates start = new CreateStates();
        BFS breadthFirstSearch = new BFS(start.getInitial(),CreateStates.getGoal());
        DFS depthFirstSearch=new DFS(start.getInitial(), CreateStates.getGoal());
        IDS iterativeDeepeningSearch =new IDS(start.getInitial(), CreateStates.getGoal());
        UCS uniformCosSearch =new UCS(start.getInitial(), CreateStates.getGoal());
        BDS bidirectionalSearch =new BDS(start.getInitial(), CreateStates.getGoal());
        AStar astar = new AStar(start.getInitial(), CreateStates.getGoal());
        GBFS greedybestfirstsearch = new GBFS(start.getInitial(),CreateStates.getGoal());
        RBFS recursivebestfirstsearch = new RBFS(start.getInitial(),CreateStates.getGoal());
//        IDAStar iterativedeepeningastarsearch = new IDAStar(start.getInitial(),CreateStates.getGoal());
        
    }
    
}
