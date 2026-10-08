public class ModifiedCDLL {
    private NodeCDLL pAwal;

    public ModifiedCDLL() {
        pAwal = null;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        pBaru.sebelum = pBaru;
        pBaru.setelah = pBaru;

        if (pAwal == null) {
            pAwal = pBaru;
        } else {
            NodeCDLL pCari = pAwal;
            while (pCari.setelah != pAwal) {
                pCari = pCari.setelah;
            }

            pBaru.sebelum = pCari;
            pBaru.setelah = pAwal;
            pAwal.sebelum = pBaru;
            pCari.setelah = pBaru;
            pAwal = pBaru;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        pBaru.sebelum = pBaru;
        pBaru.setelah = pBaru;

        if (pAwal == null) {
            pAwal = pBaru;
        } else {
            NodeCDLL pCari = pAwal;
            while (pCari.setelah != pAwal) {
                pCari = pCari.setelah;
            }

            pBaru.sebelum = pCari;
            pBaru.setelah = pAwal;
            pCari.setelah = pBaru;
            pAwal.sebelum = pBaru;
        }
    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCDLL pKini = pAwal;
            boolean ketemu = false;

            do {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                } else {
                    pKini = pKini.setelah;
                }
            } while (!ketemu && pKini != pAwal);

            if (ketemu) {
                if (pKini.setelah == pKini) {
                    pAwal = null;
                } else {
                    if (pKini == pAwal) {
                        pAwal = pAwal.setelah;
                    }

                    pKini.sebelum.setelah = pKini.setelah;
                    pKini.setelah.sebelum = pKini.sebelum;
                }
            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar);

        if (pAwal != null) {
            NodeCDLL pCetak = pAwal;
            do {
                System.out.print(pCetak.data + "->");
                pCetak = pCetak.setelah;
            } while (pCetak != pAwal);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        ModifiedCDLL cdll = new ModifiedCDLL();

        cdll.SisipDataDiAwal(new Integer(50));
        cdll.SisipDataDiAwal(new Integer(60));
        cdll.SisipDataDiAwal(new Integer(70));
        cdll.SisipDataDiAwal(new Integer(8));
        cdll.SisipDataDiAwal(new Integer(9));
        cdll.SisipDataDiAwal(new Integer(90));
        cdll.SisipDataDiAwal(new Integer(19));

        cdll.cetak("cdll Asal");

        cdll.SisipDataDiAkhir(new Integer(100));
        cdll.cetak("cdll stl sisip di akhir");

        cdll.hapusData(8);
        cdll.cetak("cdll stl 8 dihapus");
    }
}
