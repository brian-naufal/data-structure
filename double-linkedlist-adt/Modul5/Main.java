public class Main {
    public static void main(String[] args) {
        DLL list = new DLL();
        Mahasiswa mhs1 = new Mahasiswa("1111", "Karina", 4.0);
        Mahasiswa mhs2 = new Mahasiswa("1112", "Yanto", 3.4);
        Mahasiswa mhs3 = new Mahasiswa("1113", "Giselle", 3.5);
        Mahasiswa mhs4 = new Mahasiswa("1114", "Lenovo", 3.1);
        Mahasiswa mhs5 = new Mahasiswa("1115", "Stella", 3.0);
        list.insertadnSort(new Node(mhs1));
        list.insertadnSort(new Node(mhs2));
        list.insertadnSort(new Node(mhs3));
        list.insertadnSort(new Node(mhs4));
        list.insertadnSort(new Node(mhs5));

        list.printListAsc();
        System.out.println("\nAscending dan Descending\n");
        list.printListDesc();
    }
}
