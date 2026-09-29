public class DLL {
    Node head, tail;
    int size = 0;

    void inisialisasi() {
        head = null;
        tail = null;
        size = 0;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    int size() {
        return size;
    }

    void addFirst(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.next = head;
            head.prev = input;
            head = input;
        }
        size++;
    }

    void addLast(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.prev = tail;
            tail.next = input;
            tail = input;
        }
        size++;
    }

    void delete(Node input) {
        if (isEmpty()) {
            return;
        } else if (input == head && input == tail) {
            head = null;
            tail = null;
            size--;
        } else if (input == head) {
            head = input.next;
            head.prev = null;
            size--;
        } else if (input == tail) {
            tail = input.prev;
            tail.next = null;
            size--;
        } else {
            Node n = input.next;
            Node nPrev = input.prev;
            nPrev.next = n;
            n.prev = nPrev;
            size--;
        }
    }

    void insert(Node input, int index) {
        if (index < 1 || index > size + 1) {
            return;
        }
        if (index == 1) {
            addFirst(input);
            return;
        } else if (index == size + 1) {
            addLast(input);
            return;
        }
        Node n = head;
        for (int i = 1; i < index; i++) {
            n = n.next;
        }
        Node nPrev = n.prev;
        input.next = n;
        input.prev = nPrev;
        nPrev.next = input;
        n.prev = input;
        size++;
    }

    Node search(int data) {
        Node n = head;
        for (int i = 1; i <= size; i++) {
            if (n.data != null && n.data.equals(data)) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    Node access(int index) {
        if (index < 1 || index > size) {
            return null;
        }
        if (index == 1) {
            return head;
        }
        Node n = head;
        for (int i = 1; i < index; i++) {
            n = n.next;
        }
        return n;
    }

    void insertadnSort(Node input) {
        if (isEmpty()) {
            addFirst(input);
        } else if (input.data.getIpk() < head.data.getIpk()) {
            addFirst(input);
        } else if (input.data.getIpk() > tail.data.getIpk()) {
            addLast(input);
        } else {
            Node n = head;
            for (int i = 1; i < size; i++) {
                n = n.next;
                if (n.data.getIpk() > input.data.getIpk()) {
                    insert(input, i + 1);
                    return;
                }
            }
        }
    }

    void printListAsc() {
        Node n = head;
        for (int i = 1; i <= size; i++) {
            System.out.println(
                    "NIM: " + n.data.getNim() + " Nama: " + n.data.getNama() + " IPK: " + n.data.getIpk());
            n = n.next;
        }
    }

    void printListDesc() {
        Node n = tail;
        for (int i = 1; i <= size; i++) {
            System.out.println(
                    "NIM: " + n.data.getNim() + " Nama: " + n.data.getNama() + " IPK: " + n.data.getIpk());
            n = n.prev;
        }
    }
}