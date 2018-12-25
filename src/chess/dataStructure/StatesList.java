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
public class StatesList {

    public StateNode first;
    public int count = 0;

    public void reset() {       //resets a list by refrencing first to null
        first = null;
        count = 0;
    }

    public void insert(State input) {
        count++;
        StateNode p = new StateNode();
        p.data = input;
        if (first == null) {
            first = p;
        } else {
            StateNode q = first;
            while (q.next != null) {
                q = q.next;
            }
            q.next = p;
        }
    }

    public boolean find(State input) {
        StateNode p = first;
        while (p != null) {
            if (p.data.isEqual(input)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    public State remove() {
        count--;
        StateNode p = null;
        StateNode q = first;
        while (q.next != null) {
            p = q;
            q = q.next;
        }
        if (p != null) {
            p.next = null;
        }else{
            first=null;
        }
        return q.data;

    }
}
