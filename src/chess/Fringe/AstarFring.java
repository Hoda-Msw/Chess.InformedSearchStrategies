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
public class AstarFring {
    public FringeNode first;
    public int size = 0;
    
     public void insert(Node n) {
        size++;
        FringeNode p = new FringeNode();
        p.data = n;
        if (first == null) {
            first = p;
        } else {
            FringeNode prev = null;
            FringeNode curr = first;
            while (curr != null && curr.data.Fn < p.data.Fn) {
                prev = curr;
                curr = curr.next;
            }
            if (curr != null) {
                if (curr == first) {
                    p.next = curr;
                    first = p;
                } else {
                    prev.next = p;
                    p.next = curr;
                }
            } else {
                prev.next = p;
            }
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

    public int show() {
        if (first != null) {
            return first.data.Fn;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}
