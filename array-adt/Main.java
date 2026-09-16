public class Main {
    public static void main(String[] args) {

        Matrik A, B, C;

        double[][] data1 = {{1, 2, 3}, {3, 4, 7}};
        double[][] data2 = {{4, 5, 1}, {6, 1, 9}};

        A = new Matrik(data1);
        B = new Matrik(data2);

        A.cetak("A");
        B.cetak("B");

        C = Matrik.tambah(A, B);
        C.cetak("C");

        Matrik CT = Matrik.tranpos(C);
        CT.cetak("Transpos");

        Larik l1 = C.getBaris(1);
        l1.cetak("Baris ke 1 dari C");

        Larik l2 = Matrik.VektorKaliMatrik(l1, CT);
        l2.cetak("Hasil kali C.L1");
    }
}