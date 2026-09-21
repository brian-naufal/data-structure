public class SSL {
    Node head, tail;
    int size = 0;

    void inisialisasi() {
        head = null;
        tail = null;
        size = 0;
    }

    boolean isEmpty() {
        return (size == 0 || head == null);
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
            head = input;
        }
        size++;
    }

    void addLast(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    // 5. penghapusan
    void removeFirst() {
        if (!isEmpty()) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            size--;
        }
    }

    void removeLast() {
        if (!isEmpty()) {
            if (head == tail) {
                head = tail = null;
            } else {
                Node current = head;
                while (current.next != null && current.next != tail) {
                    current = current.next;
                }
                current.next = null;
                tail = current;
            }
            size--;
        }
    }

    // 6. penyisipan
    void insertAfter(Node prevNode, Node input) {
        if (prevNode != null) {
            input.next = prevNode.next;
            prevNode.next = input;
            if (prevNode == tail) {
                tail = input;
            }
            size++;
        }
    }

    void insertAt(int index, Node input) {
        if (index == 0) {
            addFirst(input);
        } else if (index == size) {
            addLast(input);
        } else if (index > 0 && index < size) {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            input.next = current.next;
            current.next = input;
            size++;
        } else {
            System.out.println("Index out of bounds");
        }
    }

    // 7. pencarian
    Node search(Object data) {
        Node current = head;
        while (current != null) {
            if (current.data != null && current.data.equals(data)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // 8. pengaksesan
    Node get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // 9. Penyisipan Terurut berdasarkan IPK (Task 4.10 No. 2)
    void insertSorted(Node input) {
        if (input == null || input.data == null)
            return;

        if (!(input.data instanceof Mahasiswa)) {
            addLast(input);
            return;
        }

        Mahasiswa inputMhs = (Mahasiswa) input.data;

        if (isEmpty()) {
            addFirst(input);
            return;
        }

        if (!(head.data instanceof Mahasiswa)) {
            addFirst(input);
            return;
        }

        Mahasiswa headMhs = (Mahasiswa) head.data;
        if (inputMhs.getIpk() <= headMhs.getIpk()) {
            addFirst(input);
            return;
        }

        if (tail.data instanceof Mahasiswa) {
            Mahasiswa tailMhs = (Mahasiswa) tail.data;
            if (inputMhs.getIpk() >= tailMhs.getIpk()) {
                addLast(input);
                return;
            }
        }

        // Kasus 4: Sisip di posisi yang sesuai di tengah list
        Node current = head;
        while (current.next != null) {
            if (current.next.data instanceof Mahasiswa) {
                Mahasiswa nextMhs = (Mahasiswa) current.next.data;
                if (inputMhs.getIpk() <= nextMhs.getIpk()) {
                    input.next = current.next;
                    current.next = input;
                    size++;
                    return;
                }
            }
            current = current.next;
        }

        // Jika sampai akhir loop belum terpasang
        addLast(input);
    }

    // Method pembantu untuk menampilkan seluruh isi Linked List
    void display() {
        if (isEmpty()) {
            System.out.println("Linked List Kosong.");
            return;
        }
        Node current = head;
        int i = 0;
        while (current != null) {
            System.out.println("[" + i + "] " + current.data);
            current = current.next;
            i++;
        }
    }
}
