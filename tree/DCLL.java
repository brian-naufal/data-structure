import java.util.Scanner;

class Player {
    String id;
    String name;
    String color;

    Player(String id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}

class NodeCDLL {
    Player data;
    NodeCDLL prev;
    NodeCDLL next;

    NodeCDLL(Player data) {
        this.data = data;
        this.prev = this;
        this.next = this;
    }
}

class CircularDoubleLinkedList {
    NodeCDLL head;      // pemain pertama (urutan duduk ke-1)
    NodeCDLL tail;      // pemain terakhir
    NodeCDLL current;   // pemain yg sdg dpt giliran
    int size;           // banyaknya pemain di meja
    boolean clockwise;  // true = ikut pointer next, false = ikut pointer prev

    // INGAT: list ini CIRCULAR !!
    // - head.prev hrs == tail
    // - tail.next hrs == head
    // - node tunggal -> prev & next nunjuk ke dirinya sendiri
    // - selama list tdk kosong, TDK ADA pointer yg bernilai null

    // =========================================================
    // TODO 1 - INITIALIZE
    // =========================================================
    // Tugas : kembalikan meja ke kondisi awal (kosong).
    // Yg hrs dibuat :
    //   - head, tail, current -> null
    //   - size -> 0
    //   - clockwise -> true (default giliran searah jarum jam)
    void initialize() {
        head = null;
        tail = null;
        current = null;
        size = 0;
        clockwise = true;
    }

    // =========================================================
    // TODO 2 - IS EMPTY
    // =========================================================
    // Tugas : cek apakah meja kosong.
    // Return : true jk tdk ada pemain, false jk ada min. 1 pemain.
    // Hint   : cukup 1 baris, bisa pakai size.
    boolean isEmpty() {
        return (size == 0);
    }

    // =========================================================
    // TODO 3 - SIZE
    // =========================================================
    // Tugas  : kembalikan banyaknya pemain yg ada di meja saat ini.
    // Return : nilai size.
    int size() {
        return size;
    }

    // =========================================================
    // TODO 4 - ADD FIRST
    // =========================================================
    // Tugas : tambah node input di urutan paling awal -> input jd head.
    // Kasus 1 - meja kosong :
    //   - prev & next milik input nunjuk ke input itu sendiri
    //   - input jd head, tail, sekaligus current
    // Kasus 2 - meja tdk kosong :
    //   - sambungkan input dgn head (input.next) & dgn tail (input.prev)
    //   - update head.prev & tail.next spy nunjuk ke input
    //   - geser head ke input
    //   - current JANGAN diubah
    // Terakhir : jgn lupa size++
    void addFirst(NodeCDLL input) {
        if (isEmpty()) {
            input.prev = input;
            input.next = input;
            head = input;
            tail = input;
            current = input;
        } else {
            input.next = head;
            input.prev = tail;
            head.prev = input;
            tail.next = input;
            head = input;
        }
        size++;
    }

    // =========================================================
    // TODO 5 - ADD LAST
    // =========================================================
    // Tugas : tambah node input di urutan paling akhir -> input jd tail.
    // Kasus 1 - meja kosong : sama persis dgn kasus kosong di TODO 4.
    // Kasus 2 - meja tdk kosong :
    //   - input.prev = tail, input.next = head
    //   - update tail.next & head.prev spy nunjuk ke input
    //   - geser tail ke input
    //   - current JANGAN diubah
    // Terakhir : jgn lupa size++
    void addLast(NodeCDLL input) {
        if (isEmpty()) {
            input.prev = input;
            input.next = input;
            head = input;
            tail = input;
            current = input;
        } else {
            tail.next = input;
            head.prev = input;
            input.prev = tail;
            input.next = head;
            tail = input;
        }
        size++;
    }

    // =========================================================
    // TODO 6 - FIND
    // =========================================================
    // Tugas  : cari node yg data.id-nya sama dgn parameter id.
    // Return : node yg ditemukan, atau null jk tdk ada / meja kosong.
    // Langkah :
    //   - jk kosong -> langsung return null
    //   - telusuri mulai dr head lewat pointer next
    //   - bandingkan id pakai .equals(), BUKAN ==
    //   - berhenti kalau sdh balik lagi ke head (1 putaran penuh)
    // AWAS : while (p != null) bakal looping tanpa henti krn circular!
    //        saran : pakai do-while.
    // current tdk boleh diubah di method ini.
    NodeCDLL find(String id) {
        if (isEmpty()) {
            return null;
        }
        NodeCDLL curr = head;
        do {
            if (curr.data.id.equals(id)) {
                return curr;
            }
            curr = curr.next;
        } while (curr != head);
        return null;
    }

    // =========================================================
    // TODO 7 - REMOVE BY ID
    // =========================================================
    // Tugas  : hapus pemain dgn id tertentu dr meja.
    // Return : true jk berhasil dihapus, false jk id tdk ditemukan.
    // Langkah :
    //   1. cari node target (manfaatkan method find). null -> return false.
    //   2. jk target satu2nya pemain (size == 1) :
    //        head, tail, current -> null, size -> 0, return true.
    //        clockwise TDK boleh di-reset.
    //   3. jk target == current, pindahkan giliran DULU sesuai arah aktif :
    //        clockwise true  -> ke target.next
    //        clockwise false -> ke target.prev
    //   4. jk target == head -> head geser ke target.next
    //      jk target == tail -> tail geser ke target.prev
    //   5. sambungkan tetangga kiri & kanan target satu sama lain
    //      (lepaskan target dr rantai).
    //   6. size--, return true.
    boolean remove(String id) {
        NodeCDLL target = find(id);
        if (target == null) {
            return false;
        }

        if (size == 1) {
            head = null;
            tail = null;
            current = null;
            size = 0;
            return true;
        }

        if (target == current) {
            if (clockwise) {
                current = target.next;
            } else {
                current = target.prev;
            }
        }

        if (target == head) {
            head = target.next;
        }
        if (target == tail) {
            tail = target.prev;
        }

        target.prev.next = target.next;
        target.next.prev = target.prev;

        size--;
        return true;
    }

    // =========================================================
    // TODO 8 - REMOVE FIRST
    // =========================================================
    // Tugas  : hapus pemain yg ada di head.
    // Return : objek Player yg dihapus, atau null jk meja kosong.
    // Hint   : simpan dulu data head ke variabel, lalu pakai remove
    //          dgn id-nya (aturan current & sambungan sdh diurus di sana).
    Player removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Player p = head.data;
        remove(p.id);
        return p;
    }

    // =========================================================
    // TODO 9 - REMOVE LAST
    // =========================================================
    // Tugas  : hapus pemain yg ada di tail.
    // Return : objek Player yg dihapus, atau null jk meja kosong.
    // Hint   : caranya mirip dgn TODO 8, tp yg diambil data tail.
    Player removeLast() {
        if (isEmpty()) {
            return null;
        }
        Player p = tail.data;
        remove(p.id);
        return p;
    }

    // =========================================================
    // TODO 10 - NEXT TURN
    // =========================================================
    // Tugas  : pindahkan current sebanyak steps kali sesuai arah aktif.
    // Return : Player yg dpt giliran stlh dipindah, atau null jk kosong.
    // Langkah :
    //   - jk kosong -> return null
    //   - clockwise true  -> tiap langkah maju lewat pointer next
    //     clockwise false -> tiap langkah mundur lewat pointer prev
    //   - di ujung list TDK berhenti, otomatis muter krn circular
    // AWAS : steps bisa sampai 2.147.483.647 -> kalau diloop satu2
    //        pasti kena time limit. Pindah sebanyak size langkah = balik
    //        ke posisi semula, jd cukup loop sebanyak (steps % size).
    Player nextTurn(int steps) {
        if (isEmpty()) {
            return null;
        }
        int move = steps % size;
        for (int i = 0; i < move; i++) {
            if (clockwise) {
                current = current.next;
            } else {
                current = current.prev;
            }
        }
        return current.data;
    }

    // =========================================================
    // TODO 11 - REVERSE DIRECTION
    // =========================================================
    // Tugas : balik arah giliran (clockwise true jd false, false jd true).
    //   - current TDK ikut pindah
    //   - tetap dijalankan walaupun meja kosong
    void reverseDirection() {
        clockwise = !clockwise;
    }

    // =========================================================
    // TODO 12 - CURRENT PLAYER
    // =========================================================
    // Tugas  : ambil pemain yg sdg dpt giliran.
    // Return : data dr current, atau null jk meja kosong.
    // AWAS   : cek null dulu sblm akses current.data
    Player currentPlayer() {
        if (isEmpty() || current == null) {
            return null;
        }
        return current.data;
    }

    // =========================================================
    // TODO 13 - PRINT FORWARD & PRINT BACKWARD
    // =========================================================
    // printForward() :
    //   - tampilkan semua pemain dr head s/d tail lewat pointer next
    //   - 1 pemain per baris dgn format : id | name | color
    //     contoh : P01 | Andi | Red
    //   - jk kosong -> tampilkan EMPTY
    //   - berhenti saat sdh balik lagi ke head (pakai do-while)
    void printForward() {
        if (isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        NodeCDLL curr = head;
        do {
            System.out.println(curr.data.id + " | " + curr.data.name + " | " + curr.data.color);
            curr = curr.next;
        } while (curr != head);
    }

    // printBackward() :
    //   - sama spt printForward, tp mulai dr tail & jalan lewat pointer prev
    //   - berhenti saat sdh balik lagi ke tail
    //   - jk kosong -> tampilkan EMPTY
    void printBackward() {
        if (isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        NodeCDLL curr = tail;
        do {
            System.out.println(curr.data.id + " | " + curr.data.name + " | " + curr.data.color);
            curr = curr.prev;
        } while (curr != tail);
    }
}

// =============================================================
// MAIN PROGRAM
// BAGIAN INI SUDAH LENGKAP - JANGAN DIUBAH
// =============================================================
public class DCLL {

    static String format(Player p) {
        return p.id + " | " + p.name + " | " + p.color;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularDoubleLinkedList game = new CircularDoubleLinkedList();
        game.initialize();

        int q = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < q; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("\\s+");
            String command = parts[0];

            switch (command) {
                case "ADD_FIRST": {
                    game.addFirst(new NodeCDLL(new Player(parts[1], parts[2], parts[3])));
                    break;
                }
                case "ADD_LAST": {
                    game.addLast(new NodeCDLL(new Player(parts[1], parts[2], parts[3])));
                    break;
                }
                case "REMOVE": {
                    System.out.println(game.remove(parts[1]) ? "SUCCESS" : "FAILED");
                    break;
                }
                case "REMOVE_FIRST": {
                    Player p = game.removeFirst();
                    System.out.println(p == null ? "NONE" : "REMOVED: " + format(p));
                    break;
                }
                case "REMOVE_LAST": {
                    Player p = game.removeLast();
                    System.out.println(p == null ? "NONE" : "REMOVED: " + format(p));
                    break;
                }
                case "FIND": {
                    NodeCDLL n = game.find(parts[1]);
                    System.out.println(n == null ? "NONE" : format(n.data));
                    break;
                }
                case "NEIGHBOR": {
                    NodeCDLL n = game.find(parts[1]);
                    if (n == null) {
                        System.out.println("NONE");
                    } else {
                        System.out.println(n.prev.data.id + " <- " + n.data.id + " -> " + n.next.data.id);
                    }
                    break;
                }
                case "NEXT": {
                    int steps = Integer.parseInt(parts[1]);
                    Player p = game.nextTurn(steps);
                    System.out.println(p == null ? "TURN: NONE" : "TURN: " + format(p));
                    break;
                }
                case "REVERSE": {
                    game.reverseDirection();
                    System.out.println(game.clockwise ? "DIRECTION: CLOCKWISE" : "DIRECTION: COUNTERCLOCKWISE");
                    break;
                }
                case "CURRENT": {
                    Player p = game.currentPlayer();
                    System.out.println(p == null ? "CURRENT: NONE" : "CURRENT: " + format(p));
                    break;
                }
                case "PRINT": {
                    game.printForward();
                    break;
                }
                case "PRINT_BACK": {
                    game.printBackward();
                    break;
                }
                case "SIZE": {
                    System.out.println("SIZE: " + game.size());
                    break;
                }
            }
        }
        sc.close();
    }
}
