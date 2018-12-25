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
public class BFSFringe {
 
    public FringeNode first;
    public int size = 0;

    public void insert(Node n) {
        size++;
        FringeNode p = new FringeNode();
        p.data = n;
        if (first == null) {
            first = p;
        } else {
            FringeNode q = first;
            while (q.next != null) {
                q = q.next;
            }
            q.next = p;
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

    public Node get(int index) {
        FringeNode output = first;
        if (index < size) {
            for (int i = 0; i < index; i++) {
                output = output.next;
            }
            return output.data;
        }
        return null;
    }
}
