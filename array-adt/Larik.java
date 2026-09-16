public class Larik {
    private int ukuran;
    private double[] itemDt;

    /**
     * constructor untuk membuat suatu larik
     * @param ukuran: ukuran larik
     */
    public Larik(int ukuran) {
        this.ukuran = ukuran;
        this.itemDt = new double[ukuran];
    }

    /**
     * fungsi untuk mendapatkan ukuran larik
     * @return ukuran larik
     */
    public int getSize() {
        return ukuran;
    }

    /**
     * fungsi untuk mendapatkan item larik
     * @param id: indeks item
     * @return nilai item
     */
    public double getItem(int id) {
        return itemDt[id];
    }

    /**
     * procedure untuk mengisi item larik
     * @param id: indeks item
     * @param dt: nilai yang akan dimasukkan
     */
    public void isiItem(int id, double dt) {
        itemDt[id] = dt;
    }

    /**
     * fungsi perkalian antara dua larik
     * @param A: Larik pertama
     * @param B: Larik kedua
     * @return hasil perkalian
     */
    public static double LarikKaliLarik(Larik A, Larik B) {
        double hasil = 0;

        if (A.getSize() == B.getSize()) {
            for (int i = 0; i < A.getSize(); i++) {
                hasil += A.getItem(i) * B.getItem(i);
            }
        }

        return hasil;
    }

    /**
     * procedure cetak
     * @param kom: komentar
     */
    public void cetak(String kom) {
        System.out.println(kom);

        for (int i = 0; i < ukuran; i++) {
            System.out.printf("%.2f ", itemDt[i]);
        }

        System.out.println();
    }
}