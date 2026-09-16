public class Matrik {
    private int nBaris, nKolom;
    private double[][] itemDt;

    /**
     * constructor untuk membuat suatu matrik
     * @param nBrs: banyaknya baris
     * @param nKlm: banyaknya kolom
     */
    public Matrik(int nBrs, int nKlm) {
        nBaris = nBrs;
        nKolom = nKlm;
        itemDt = new double[nBaris][nKolom];
    }

    /**
     * constructor untuk membuat matrik dari array 2 dimensi
     * @param A: array dua dimensi
     */
    public Matrik(double[][] A) {
        this(A.length, A[0].length);

        for (int i = 0; i < nBaris; i++) {
            for (int j = 0; j < nKolom; j++) {
                this.itemDt[i][j] = A[i][j];
            }
        }
    }

    /**
     * Fungsi untuk mendapatkan jumlah baris
     * @return jumlah baris
     */
    public int getNBaris() {
        return nBaris;
    }

    public int getNKolom() {
        return nKolom;
    }

    public double getItem(int idB, int idK) {
        return this.itemDt[idB][idK];
    }

    public void setItem(int idB, int idK, double dt) {
        this.itemDt[idB][idK] = dt;
    }

    /**
     * fungsi tambah antara dua matrik A dan B
     * @param A: Matrik
     * @param B: Matrik
     * @return Matrik hasil
     */
    public static Matrik tambah(Matrik A, Matrik B) {
        Matrik hasil = null;

        if (A.getNBaris() == B.getNBaris()
                && A.getNKolom() == B.getNKolom()) {

            hasil = new Matrik(A.getNBaris(), A.getNKolom());

            for (int i = 0; i < A.getNBaris(); i++) {
                for (int j = 0; j < A.getNKolom(); j++) {
                    double nilai = A.getItem(i, j) + B.getItem(i, j);
                    hasil.setItem(i, j, nilai);
                }
            }
        }

        return hasil;
    }

    /**
     * fungsi static perkalian antara vektor dengan matrik
     * Syarat: lebar L sama dengan jumlah baris M
     * @param L: Vector (Larik)
     * @param M: Matrik
     * @return Vector (Larik) berdimensi nKolom dari M
     */
    public static Larik VektorKaliMatrik(Larik L, Matrik M) {
        Larik lHasil = null;
        Larik lKolom = null;

        if (L.getSize() == M.getNBaris()) {
            lHasil = new Larik(M.getNKolom());

            for (int i = 0; i < M.getNKolom(); i++) {
                lKolom = M.getKolom(i);

                double hasil = Larik.LarikKaliLarik(L, lKolom);

                System.out.println(hasil);
                lHasil.isiItem(i, hasil);
            }
        }

        return lHasil;
    }

    /**
     * fungsi static tranpos suatu matrik
     * @param A: Matrik
     * @return Matrik tranpos
     */
    public static Matrik tranpos(Matrik A) {
        Matrik hasil = new Matrik(A.getNKolom(), A.getNBaris());

        for (int i = 0; i < A.getNBaris(); i++) {
            for (int j = 0; j < A.getNKolom(); j++) {
                hasil.setItem(j, i, A.getItem(i, j));
            }
        }

        return hasil;
    }

    /**
     * fungsi untuk mendapatkan vektor baris dari matrik
     * @param idBaris: indek baris yang akan diekstrak
     * @return Larik representasi baris
     */
    public Larik getBaris(int idBaris) {
        Larik l = new Larik(this.nKolom);

        for (int i = 0; i < this.nKolom; i++) {
            double itemBaris = this.getItem(idBaris, i);
            l.isiItem(i, itemBaris);
        }

        return l;
    }

    /**
     * fugsi untuk mendapatkan vektor kolom suatu matrik
     * @param idKolom: id kolom yang akan diekstrak
     * @return Larik representasi kolom
     */
    public Larik getKolom(int idKolom) {
        Larik l = new Larik(this.nBaris);

        for (int i = 0; i < this.nBaris; i++) {
            double itemKolom = this.getItem(i, idKolom);
            l.isiItem(i, itemKolom);
        }

        return l;
    }

    /**
     * procedure cetak
     * @param kom
     */
    public void cetak(String kom) {
        System.out.println(kom);

        for (int i = 0; i < this.nBaris; i++) {
            for (int j = 0; j < this.nKolom; j++) {
                System.out.printf("%.2f ", this.itemDt[i][j]);
            }

            System.out.println();
        }
    }
}