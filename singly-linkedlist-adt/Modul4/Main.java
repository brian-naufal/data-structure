public class Main {
    public static void main(String[] args) {
        SSL list = new SSL();

        System.out.println("=== Demonstrasi Penambahan Terurut Berdasarkan IPK ===");

        Mahasiswa mhs1 = new Mahasiswa("23001", "Budi", 3.50);
        Mahasiswa mhs2 = new Mahasiswa("23002", "Ani", 3.85);
        Mahasiswa mhs3 = new Mahasiswa("23003", "Cici", 3.20);
        Mahasiswa mhs4 = new Mahasiswa("23004", "Dodi", 4.00);
        Mahasiswa mhs5 = new Mahasiswa("23005", "Eka", 3.65);

        System.out.println("Menambahkan Budi (IPK: 3.50)...");
        list.insertSorted(new Node(mhs1));

        System.out.println("Menambahkan Ani (IPK: 3.85)...");
        list.insertSorted(new Node(mhs2));

        System.out.println("Menambahkan Cici (IPK: 3.20)...");
        list.insertSorted(new Node(mhs3));

        System.out.println("Menambahkan Dodi (IPK: 4.00)...");
        list.insertSorted(new Node(mhs4));

        System.out.println("Menambahkan Eka (IPK: 3.65)...");
        list.insertSorted(new Node(mhs5));

        System.out.println("\n--- Hasil Linked List (Urut berdasarkan IPK) ---");
        list.display();
    }
}
