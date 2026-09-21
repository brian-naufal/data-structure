import java.util.LinkedList;

public class LinkyLi {
    private node head, tail;
    private int size;

    public void addFirst(int data) {
        node nN = new node(data);
        nN.next = head;
        head = nN;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void addLast() {

    }



}


class node {
    node next;
    int data;

    node(int data) {
        this.next = null;
        this.data = data;
    }
}