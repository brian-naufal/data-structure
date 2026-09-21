public class Mahasiswa {
    String nim;
    String nama;
    double ipk;

    public Mahasiswa(String nim, String nama, double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.ipk = ipk;
    }

    public double getIpk() {
        return ipk;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return "NIM: " + nim + ", Nama: " + nama + ", IPK: " + ipk;
    }
}
