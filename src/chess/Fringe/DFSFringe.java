/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Fringe;

import chess.dataStructure.Node;

/**
 *
 * @author Hoda_msw
 */
public class DFSFringe {
    
    public FringeNode first;
    public int size = 0;

    public void insert(Node n) {
        size++;
        FringeNode p = new FringeNode();
        p.data = n;
        if (first == null) {
            first = p;
        } else {
            p.next = first;
            first = p;
        }
    }

    public Node remove() {
        if (first != null) {
            size--;
            Node output = first.data;
            first = first.next;
            return output;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
    
}
