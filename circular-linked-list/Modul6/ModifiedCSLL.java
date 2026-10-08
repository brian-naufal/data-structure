public class ModifiedCSLL {
    private NodeCSLL pAwal;

    public ModifiedCSLL() {
        pAwal = null;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        pBaru.setelah = pBaru;

        if (pAwal == null) {
            pAwal = pBaru;
        } else {
            NodeCSLL pCari = pAwal;
            while (pCari.setelah != pAwal) {
                pCari = pCari.setelah;
            }

            pBaru.setelah = pAwal;
            pCari.setelah = pBaru;
            pAwal = pBaru;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        pBaru.setelah = pBaru;

        if (pAwal == null) {
            pAwal = pBaru;
        } else {
            NodeCSLL pCari = pAwal;
            while (pCari.setelah != pAwal) {
                pCari = pCari.setelah;
            }

            pCari.setelah = pBaru;
            pBaru.setelah = pAwal;
        }
    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCSLL pSbl = null;
            NodeCSLL pKini = pAwal;
            boolean ketemu = false;

            do {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                } else {
                    pSbl = pKini;
                    pKini = pKini.setelah;
                }
            } while (!ketemu && pKini != pAwal);

            if (ketemu) {
                if (pKini == pAwal) {
                    if (pAwal.setelah == pAwal) {
                        pAwal = null;
                    } else {
                        NodeCSLL pCari = pAwal;
                        while (pCari.setelah != pAwal) {
                            pCari = pCari.setelah;
                        }

                        pAwal = pAwal.setelah;
                        pCari.setelah = pAwal;
                    }
                } else {
                    pSbl.setelah = pKini.setelah;
                }
            }
        }
    }

    public void hapusSatuDataDiAwal() {
        if (pAwal != null) {
            if (pAwal.setelah == pAwal) {
                pAwal = null;
            } else {
                NodeCSLL pCari = pAwal;
                while (pCari.setelah != pAwal) {
                    pCari = pCari.setelah;
                }

                pAwal = pAwal.setelah;
                pCari.setelah = pAwal;
            }
        }
    }

    public void hapusSatuDataDiAkhir() {
        if (pAwal != null) {
            if (pAwal.setelah == pAwal) {
                pAwal = null;
            } else {
                NodeCSLL pSbl = pAwal;
                while (pSbl.setelah.setelah != pAwal) {
                    pSbl = pSbl.setelah;
                }

                pSbl.setelah = pAwal;
            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar);

        if (pAwal != null) {
            NodeCSLL pCetak = pAwal;
            do {
                System.out.print(pCetak.data + "->");
                pCetak = pCetak.setelah;
            } while (pCetak != pAwal);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        ModifiedCSLL csll = new ModifiedCSLL();

        csll.SisipDataDiAwal(new Integer(50));
        csll.SisipDataDiAwal(new Integer(60));
        csll.SisipDataDiAwal(new Integer(70));
        csll.SisipDataDiAwal(new Integer(8));
        csll.SisipDataDiAwal(new Integer(9));
        csll.SisipDataDiAwal(new Integer(90));
        csll.SisipDataDiAwal(new Integer(19));

        csll.cetak("csll Asal");

        csll.SisipDataDiAkhir(new Integer(100));
        csll.cetak("csll stl sisip di akhir");

        csll.hapusData(8);
        csll.cetak("csll stl 8 dihapus");

        csll.hapusSatuDataDiAwal();
        csll.cetak("csll stl hapus satu data di awal");

        csll.hapusSatuDataDiAkhir();
        csll.cetak("csll stl hapus satu data di akhir");
    }
}
